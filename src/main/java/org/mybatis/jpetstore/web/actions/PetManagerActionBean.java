package org.mybatis.jpetstore.web.actions;

import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.integration.spring.SpringBean;
import org.mybatis.jpetstore.domain.PetManager;
import org.mybatis.jpetstore.service.PetManagerService;

import java.util.List;

public class PetManagerActionBean extends AbstractActionBean {

    @SpringBean
    private transient PetManagerService petManagerService;

    private List<PetManager> petManagerList;

    public List<PetManager> getPetManagerList() {
        return petManagerList;
    }

    public void setPetManagerList(List<PetManager> petManagerList) {
        this.petManagerList = petManagerList;
    }

    private static final String VIEW_MANAGER_LIST = "/WEB-INF/jsp/chatting/allManagerList.jsp";

    public ForwardResolution allManagerList() {
        petManagerList = petManagerService.getPetMangerList();
        return new ForwardResolution(VIEW_MANAGER_LIST);
    }

}
