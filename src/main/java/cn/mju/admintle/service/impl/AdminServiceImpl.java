package cn.mju.admintle.service.impl;

import cn.mju.admintle.domain.*;
import cn.mju.admintle.mapper.*;
import cn.mju.admintle.service.AdminService;
import cn.mju.admintle.service.PubService;
import cn.mju.admintle.utils.RedisUtil;
import cn.mju.admintle.vo.NoticeVo;
import cn.mju.admintle.vo.UserVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {

    private static final Logger log = LoggerFactory.getLogger(AdminServiceImpl.class);

    private static final String ALL_USER = "allusers";

    private static final String ALL_DEPT = "alldepts";

    private static final String ALL_JOB = "alljobs";

    private static final String DEPT = "dept:";




    @Autowired
    private UserMapper userMapper;
    @Autowired
    private DeptMapper deptMapper;
    @Autowired
    private JobMapper jobMapper;
    @Autowired
    private PubService pubService;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private FileMapper fileMapper;
    @Autowired
    private NoticeMapper noticeMapper;
    @Autowired
    private SignMapper signMapper;
    @Autowired
    private LeaveMapper leaveMapper;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private RedisUtil.redisString redisString;
    @Autowired
    private HealthMapper healthMapper;

    /**
     * 条件组合查询
     * @param username
     * @param deptName
     * @param jobName
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public PageInfo<User> getUserByCondition(String username, String deptName, String jobName,int pageNum, int pageSize) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("deptName",deptName);
        map.put("jobName",jobName);
        map.put("username",username);
        ArrayList<Integer> jobIds = new ArrayList<>();
        ArrayList<Integer> deptIds = new ArrayList<>();
        if (!username.equals("") && deptName.equals("") && jobName.equals("")){
            return pubService.getPage(pageNum,pageSize,map);

        }
        if (username.equals("") && deptName.equals("") && !jobName.equals("")){
            List<Job> jobs = jobMapper.getJobByName(map);
            for (Job job : jobs) {
                jobIds.add(job.getId());
            }
            map.put("jobId",jobIds);
            return pubService.getPage(pageNum,pageSize,map);
        }
        if (username.equals("") && !deptName.equals("") && jobName.equals("")){
            List<Dept> deptByName = deptMapper.getDeptByName(map);
            for (Dept dept : deptByName) {
                deptIds.add(dept.getId());
            }
            map.put("deptId",deptIds);
            return pubService.getPage(pageNum,pageSize,map);
        }else{
            List<Dept> deptByName = deptMapper.getDeptByName(map);
            for (Dept dept : deptByName) {
                deptIds.add(dept.getId());
            }
            map.put("deptId",deptIds);
            List<Job> jobByName = jobMapper.getJobByName(map);
            for (Job job : jobByName) {
                jobIds.add(job.getId());
            }
            map.put("deptId",deptIds);
            map.put("jobId",jobIds);
            return pubService.getPage(pageNum,pageSize,map);
        }

    }

    /**
     * 添加员工
     */
    @Override
    public boolean addUser(User user,String roleName)  {
        boolean i = userMapper.addUser(user) >0;
        //添加员工时自动添加档案以及角色信息
        if (i){
            try {
                addFile(user);
            }catch (Exception e){
                e.printStackTrace();
            }
            Role role = new Role();
            role.setUserId(user.getId());
            role.setRoleName(roleName);
            roleMapper.addRole(role);
            return true;
        }else {
            return false;
        }


    }

    @Override
    public boolean update(User user,String roleName) {
        //修改部门和职位信息自动添加档案记录
        User dbuser = userMapper.getUserById(user.getId());

        //修改角色
        Role role = new Role();
        role.setUserId(user.getId());
        role.setRoleName(roleName);
        roleMapper.updateRole(role);

        boolean flag = userMapper.updateUser(user) >0;
        if (user.getDeptId()!=dbuser.getDeptId() || user.getJobId()!=dbuser.getJobId()){
            //旧档案添加离职日期
            List<File> fileByUserId = fileMapper.getFileByUserId(user.getId());

            File oldFile = fileByUserId.get(fileByUserId.size() - 1);

            oldFile.setQuitTime(new Date());


            fileMapper.updateFile(oldFile);
            //添加新档案
            try {
                addFile(user);
            }catch (Exception e){
                e.printStackTrace();
            }

        }
        return flag;

    }

    private void addFile(User user) throws Exception{
        File file = new File();
        file.setUserId(user.getId());
        file.setEntryTime(new Date());
        file.setDeptName(deptMapper.getDeptById(userMapper.getUserById(user.getId()).getDeptId()).getDeptName());
        file.setJobName(jobMapper.getJobById(userMapper.getUserById(user.getId()).getJobId()).getJobName());
        fileMapper.insertFile(file) ;
    }

    @Override
    public boolean delete(Long id) {
        boolean flag = userMapper.deleteUser(id) >0;
        if (flag){
            roleMapper.deleteRole(id);
            fileMapper.delete(id);
            noticeMapper.deleteByUser(id);
            signMapper.deleteSignByUserId(id);
            leaveMapper.deleteLeaveByUserId(id);
            healthMapper.delete(id);
            return true;
        }else{
            return false;
        }

    }

    @Override
    public boolean delteBatch(Long[] ids) {
        List<Long> cids = new ArrayList<>(Arrays.asList(ids));
        boolean flag = userMapper.deleleteBatch(cids) >0;
        if (flag){
            roleMapper.deleteBatch(cids);
            fileMapper.deleleteBatch(cids);
            noticeMapper.deleleteBatchByUserId(cids);
            signMapper.deleleteBatchByUserIdS(cids);
            leaveMapper.deleleteBatchByUserIdS(cids);
            healthMapper.deleleteBatch(cids);
            return true;
        } else {
            return false;
        }

    }

    @Override
    public PageInfo<Role> getRolesByName(int pageNum, int pageSize, String roleName) {
        PageHelper.startPage(pageNum,pageSize);
        List<Role> roles = roleMapper.getRolesByName(roleName);
        PageInfo<Role> page = new PageInfo<>(roles);
        return page;
    }

    @Override
    public PageInfo<File> getAllFiles(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<File> all = fileMapper.getAll();
        PageInfo<File> pageInfo = new PageInfo<>(all);
        return pageInfo;
    }

    @Override
    public PageInfo<File> searchFile(String userName,int pageNum, int pageSize) {
        ArrayList<Long> ids = new ArrayList<>();
        HashMap<String, Object> map = new HashMap<>();
        map.put("username",userName);
        List<User> users = userMapper.getUserByName(map);
        for (User user : users) {
            Long id = user.getId();
            ids.add(id);
        }
        PageHelper.startPage(pageNum,pageSize);
        List<File> files = fileMapper.getFileByIds(ids);
        PageInfo<File> filePageInfo = new PageInfo<>(files);
        return filePageInfo;
    }

    @Override
    public boolean quitEmp(Long id) {
        File file = fileMapper.getFileById(id);
        file.setQuitTime(new Date());
        boolean flag = fileMapper.updateFile(file) >0;
        if (flag){
            User user = userMapper.getUserById(file.getUserId());
            user.setState(0);
            boolean i= userMapper.updateUser(user)>0;
            return i;
        }else {
            return false;
        }

    }

    @Override
    public List<Dept> getDepts() {
        List<Dept> list= new ArrayList<>();
        if (redisUtil.hasKey(ALL_DEPT)){
            log.info("从redis中获取数据.");
            list = (List<Dept>) redisString.get(ALL_DEPT);
        }else{
            log.info("从mysql中获取数据.");
            list= deptMapper.getDeptData();
            log.info("将数据存入redis...");
            redisString.set(ALL_DEPT, list);
        }
        return list;
    }


    @Override
    public List<Job> getJobs() {
        List<Job> list= new ArrayList<>();
        if (redisUtil.hasKey(ALL_JOB)){
            //获取数组并转化为实体的集合
            list = (List<Job>) redisString.get(ALL_JOB);
        }else{
            list= jobMapper.getJobData();
            redisString.set(ALL_JOB, list);
        }
        return list;
    }

    @Override
    public Dept getDeptAndUsers(int id) {
        Dept deptAndUser = deptMapper.getDeptAndUser(id);
        return deptAndUser;
    }

    @Override
    public boolean checkDept(int id) {
        return deptMapper.getDeptById(id) !=null;
    }

    @Override
    public boolean addDept(Dept dept) {
        boolean flag= deptMapper.addDept(dept) >0;
        redisUtil.del(ALL_DEPT);
        redisString.set(DEPT+dept.getId(),dept);
        return flag;
    }

    @Override
    public boolean updateDept(Dept dept) {
        boolean flag= deptMapper.updateDept(dept) >0;
        redisUtil.del(ALL_DEPT);
        redisString.set(DEPT+dept.getId(),dept);
        return flag;
    }

    @Override
    public PageInfo<Notice> getAllNotice(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Notice> notices = noticeMapper.findAll();
        PageInfo<Notice> noticePageInfo = new PageInfo<>(notices);
        return noticePageInfo;
    }

    @Override
    public NoticeVo findNotice(Integer id) {
        Notice notice = noticeMapper.getNotice(id);
        NoticeVo noticeVo = new NoticeVo();
        noticeVo.setTitle(notice.getHead());
        noticeVo.setUserName(userMapper.getUserById(notice.getUserId()).getUsername());
        BeanUtils.copyProperties(notice,noticeVo);
        return noticeVo;
    }

    @Override
    public PageInfo<Notice> searchNotice(String title,int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Notice> noticesByTitle = noticeMapper.getNoticesByTitle(title);
        PageInfo<Notice> noticePageInfo = new PageInfo<>(noticesByTitle);
        return noticePageInfo;
    }

    @Override
    public boolean publishNotice(Notice notice, HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        notice.setUserId(user.getId());
        notice.setCreateTime(new Date());
        boolean flag = noticeMapper.addNotice(notice) >0;
        return flag;
    }


    @Override
    public boolean delteBatchNotice(Integer[] ids) {
        List<Integer> cids = new ArrayList<>(Arrays.asList(ids));
        boolean flag = noticeMapper.deleleteBatch(cids) >0;
        return flag;
    }

    @Override
    public boolean deleteNotice(Integer id) {
        boolean flag = noticeMapper.delete(id)>0;
        return flag;
    }

    @Override
    public List<UserVo> downloadUser() {
        List<User> users = userMapper.getUsers();
        List<UserVo> userVos = users.stream().map(e -> (
                new UserVo(e.getId(),e.getUsername(), e.getAddress(), e.getBirthday(), e.getEmail(), e.getPhone(),
                        deptMapper.getDeptById(e.getDeptId()).getDeptName(), jobMapper.getJobById(e.getJobId()).getJobName(),
                        e.getState()
                )
        )).collect(Collectors.toList());
        return userVos;
    }

    @Override
    public NoticeVo getLatest() {
        List<Notice> list = noticeMapper.findAll();
        Notice notice = list.get(0);
        NoticeVo noticeVo = new NoticeVo();
        noticeVo.setTitle(notice.getHead());
        noticeVo.setUserName(userMapper.getUserById(notice.getUserId()).getUsername());
        BeanUtils.copyProperties(notice,noticeVo);
        return noticeVo;
    }

    @Override
    public boolean updatePass(User user) {
        User user1 = pubService.passwordToMD5(user);
        boolean flag = userMapper.updateUser(user1) >0;
        return flag;
    }


}
