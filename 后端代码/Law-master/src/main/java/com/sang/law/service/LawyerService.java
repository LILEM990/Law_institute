package com.sang.law.service;

import com.sang.law.pojo.Lawyer;


import java.util.List;

public interface LawyerService {
    List<Lawyer> findAllLawyer();

    boolean addLawyer(Lawyer lawyer);

    boolean deleteLawyerById(String id);

    Lawyer getLawyer(String id);

    Lawyer loginLawyer(String lawyer_username, String lawyer_password);

    boolean updateLawyer(Lawyer lawyer);
}
