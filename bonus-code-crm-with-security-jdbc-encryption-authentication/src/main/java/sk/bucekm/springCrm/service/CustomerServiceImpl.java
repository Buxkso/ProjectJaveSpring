package sk.bucekm.springCrm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sk.bucekm.springCrm.dao.CustomerDAO;
import sk.bucekm.springCrm.entity.CrmUser;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    // need to inject customer dao
    @Autowired
    private CustomerDAO customerDAO;

    @Override
    @Transactional
    public List<CrmUser> getCrmUsers() {
        return customerDAO.getCrmUsers();
    }

    @Override
    @Transactional
    public void saveCrmUser(CrmUser thecrmUser) {

        customerDAO.saveCrmUser(thecrmUser);
    }

    @Override
    @Transactional
    public CrmUser getCrmUser(String theUsername) {

        return customerDAO.getCrmUser(theUsername);
    }

    @Override
    @Transactional
    public void deleteCrmUser(String theUsername) {

        customerDAO.deleteCrmUser(theUsername);
    }
}





