package com.qw.provideruserinformation.services;


import com.qw.provideruserinformation.dao.CreateDao;
import com.qw.provideruserinformation.entity.Userinformation;
import org.springframework.stereotype.Service;

/**
 * @author Eclair
 */
@Service
public class CreateService {
    private final CreateDao createDao;


    public CreateService(CreateDao createDao) {
        this.createDao = createDao;

    }

    /**
     * 添加
     */
    public Userinformation createUserInformation(Userinformation UserInformation) {

        return createDao.createUserInformation(UserInformation);

    }
}
