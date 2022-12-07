package org.mybatis.jpetstore.web.actions;

import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sourceforge.stripes.action.FileBean;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.integration.spring.SpringBean;

import org.mybatis.jpetstore.domain.Account;
import org.mybatis.jpetstore.domain.PetManager;
import org.mybatis.jpetstore.service.AccountService;
import org.mybatis.jpetstore.service.PetManagerService;

public class PetManagerActionBean extends AbstractActionBean {

    private String name;
    private String password;

    private String repeatedPassword;
    private String email;
    private String firstName;
    private String lastName;
    private String address1;
    private String address2;
    private String city;
    private String state;
    private String zip;
    private String country;
    private String phone;

    private String petType;
    private String age;
    private String since;
    private String manage;

    private FileBean photo;

    private PetManager petManager;

    private Account account;

    @SpringBean
    private transient AccountService accountService;
    @SpringBean
    private transient PetManagerService petManagerService;
    private static final List<String> PET_TYPE_LIST;

    static {
        PET_TYPE_LIST = Collections.unmodifiableList(Arrays.asList("CAT/DOG", "REPTILE/FISH", "BIRD"));
    }

    public Account getAccount() {
        return account;
    }

    public String getRepeatedPassword() {
        return repeatedPassword;
    }

    public void setRepeatedPassword(String repeatedPassword) {
        this.repeatedPassword = repeatedPassword;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public PetManager getPetManager() {
        return petManager;
    }

    public void setPetManager(PetManager petManager) {
        this.petManager = petManager;
    }

    public FileBean getPhoto() {
        return photo;
    }

    public void setPhoto(FileBean photo) {
        this.photo = photo;
    }

    private List<PetManager> petManagerList;

    public List<String> getPetTypeList() {
        return PET_TYPE_LIST;
    }
    public String getPetType() {
        return petType;
    }

    public void setPetType(String petType) {
        this.petType = petType;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getSince() {
        return since;
    }

    public void setSince(String since) {
        this.since = since;
    }

    public String getManage() {
        return manage;
    }

    public void setManage(String manage) {
        this.manage = manage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<PetManager> getPetManagerList() {
        return petManagerList;
    }

    public void setPetManagerList(List<PetManager> petManagerList) {
        this.petManagerList = petManagerList;
    }

    private static final String VIEW_MANAGER_LIST = "/WEB-INF/jsp/manager/allManagerList.jsp";

    private static final String NEW_ACCOUNT = "/WEB-INF/jsp/manager/NewPetManagerForm.jsp";
    private static final String ADMIN_DASHBOARD_CHOOSING = "/WEB-INF/jsp/catalog/AdminDashboardChoosing.jsp";

    private static final String EDIT_ACCOUNT = "/WEB-INF/jsp/manager/EditPetManagerForm.jsp";

    private static final String VIEW_DELETION_CHECK = "/WEB-INF/jsp/manager/PetManagerDeletingCheck.jsp";

    public Resolution newAccountForm() {
        return new ForwardResolution(NEW_ACCOUNT);
    }
    public ForwardResolution allManagerList() {
        petManagerList = petManagerService.getPetMangerList();
        System.out.println(petManagerList.get(0));
        return new ForwardResolution(VIEW_MANAGER_LIST);
    }

    public ForwardResolution editPetManagerAccount() {
        String managerId = context.getRequest().getParameter("managerId");
        account = accountService.getPetManagerAccount(managerId);
        petManager = petManagerService.getPetMangerByID(managerId);
        return new ForwardResolution(EDIT_ACCOUNT);
    }

    public Resolution editAccount() {
        HttpServletRequest request = context.getRequest();
        String path = request.getServletContext().getRealPath("images") + "/" + account.getUsername() + ".jpeg";
        petManager.setPetType(petType);
        petManager.setName(account.getFirstName());
        petManager.setManagerId(account.getUsername());
        petManager.setCatdog(petType.equals("CAT/DOG"));
        petManager.setRepfish(petType.equals("REPTILE/FISH"));
        petManager.setBird(petType.equals("BIRD"));
        if(Integer.parseInt(petManager.getAge())<=0 || Integer.parseInt(petManager.getSince())<0){
            setMessage("ERROR: Value is negative.");
            return new ForwardResolution(EDIT_ACCOUNT);
        }
        try {
            accountService.editPetManagerAccount(account);
            petManagerService.editPetManager(petManager);
            photo.save(new File(path));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            setMessage("ERROR: Fill in the blank or Submit your photo");
            return new ForwardResolution(EDIT_ACCOUNT);
        }
        return new RedirectResolution(PetManagerActionBean.class,"allManagerList");
    }

    public Resolution deletePetManagerAccount() {
        HttpSession session = context.getRequest().getSession();
        String permission = (String) session.getAttribute("permission");
        String managerId = context.getRequest().getParameter("managerId");
        if(!permission.equals("admin")){
            setMessage("You don't have permission.");
            return new ForwardResolution(ERROR);
        }
        petManagerService.deletePetManager(managerId);
        accountService.deleteAccountByUserId(managerId);
        return new RedirectResolution(PetManagerActionBean.class,"allManagerList");
    }

    public ForwardResolution viewDeletionCheck(){
        HttpSession session = context.getRequest().getSession();
        String permission = (String) session.getAttribute("permission");
        name = context.getRequest().getParameter("managerId");
        if(!permission.equals("admin")){
            setMessage("You don't have permission to access");
            return new ForwardResolution(ERROR);
        }
        return new ForwardResolution(VIEW_DELETION_CHECK);
    }

    public Resolution newAccount() {
        HttpServletRequest request = context.getRequest();
        String path = request.getServletContext().getRealPath("images") + "/" + name + ".jpeg";
        try {
            photo.save(new File(path));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            setMessage("File Upload Fail");
            return new ForwardResolution(ERROR);
        }
        account = new Account();
        account.setUsername(name);
        account.setPassword(password);
        account.setCity(city);
        account.setEmail(email);
        account.setFirstName(firstName);
        account.setLastName(lastName);
        account.setAddress1(address1);
        account.setAddress2(address2);
        account.setState(state);
        account.setZip(zip);
        account.setCountry(country);
        account.setPhone(phone);
        petManager = new PetManager();
        if (name == null || password==null || firstName == null || age == null || since == null ||
                manage == null||email==null||address1==null||address2==null||state==null||
                zip==null||country==null||phone==null) {
            setMessage("Fill in the Blank.");
            return new ForwardResolution(NEW_ACCOUNT);
        }
        petManager.setManagerId(name);
        petManager.setPetType(petType);
        petManager.setName(firstName);
        petManager.setAge(age);
        petManager.setSince(since);
        petManager.setManage(manage);
        petManager.setCatdog(petType.equals("CAT/DOG"));
        petManager.setRepfish(petType.equals("REPTILE/FISH"));
        petManager.setBird(petType.equals("BIRD"));
        if(Integer.parseInt(age)<=0 || Integer.parseInt(since)<0){
            setMessage("ERROR: Value is negative.");
            return new ForwardResolution(NEW_ACCOUNT);
        }
        try{
            accountService.insertPetManagerAccount(account);
            petManagerService.insertPetManager(petManager);
        }catch (Exception e){
            setMessage("ERROR: fill in the blank OR your ID is duplicated.");
            return new ForwardResolution(NEW_ACCOUNT);
        }
        return new RedirectResolution(PetManagerActionBean.class,"allManagerList");
    }

}
