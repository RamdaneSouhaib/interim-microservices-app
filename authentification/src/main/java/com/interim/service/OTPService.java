package com.interim.service;

import com.interim.model.OTP;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.ws.rs.NotFoundException;

@ApplicationScoped
public class OTPService {

    @Inject
    EntityManager em;
    @Transactional
    public void create(OTP otp) {
        OTP otpNew = OTP.find("email", otp.getEmail()).firstResult();
        if (otpNew != null) {
            otpNew.setOtpCode(otp.getOtpCode());
            em.persist(otpNew);
        }else
            em.persist(otp);
    }


    @Transactional
    public void update(OTP otp) {
        OTP entity = OTP.findById(otp.getId());
        if (entity == null) {
            throw new NotFoundException("OTP not found");
        }
        entity.setEmail(otp.getEmail());
        entity.setOtpCode(otp.getOtpCode());
    }

    @Transactional
    public void delete(Long id) {
        OTP entity = OTP.findById(id);
        if (entity == null) {
            throw new NotFoundException("OTP not found");
        }
        entity.delete();
    }

    public OTP findById(Long id) {
        return OTP.findById(id);
    }

    public OTP verifyOTP(String email, String otpCode) {
        OTP otp = OTP.find("email = ?1 ", email).firstResult();
        return otp ;
    }
    @Transactional
    public void deleteByEmail(String email) {
        OTP.delete("email", email);
    }
}
