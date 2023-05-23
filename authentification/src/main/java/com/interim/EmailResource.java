package com.interim;


import com.interim.model.OTP;
import com.interim.service.OTPService;
import io.quarkus.mailer.Mail;
import io.quarkus.mailer.Mailer;
import io.quarkus.mailer.reactive.ReactiveMailer;
import io.quarkus.vertx.web.Body;
import io.smallrye.common.annotation.Blocking;
import io.smallrye.mutiny.Uni;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/")
@ApplicationScoped
public class EmailResource {

    @Inject
    Mailer mailer;
    @Inject
    ReactiveMailer reactiveMailer;
    @Inject
    OTPService otpService;

    @GET
    @Path("/email12")
    public String sayHello() {
        return "hello";
    }

    @POST
    @Path("/email")
    @Blocking
    public Uni<Void> sendEmail(@Body String email) {

        System.out.println("email = " +email);
        email = email.replace("\"", "");
        // Generate a 6-digit random number for the OTP code
        int otpCode = (int) (Math.random() * 900000) + 100000;
        System.out.println("otpCode = " +otpCode);
        //OTP otp = new OTP(email, String.valueOf(otpCode));   // Desactivated --> for test
        OTP otp = new OTP(email, String.valueOf(111111));
        System.out.println("email = " +email.toString());
        otpService.create(otp);
        return reactiveMailer.send(
                Mail.withText(email,
                        "StaffNow Email Verification",
                        "Dear user,\n" +
                                "\n" +
                                "You have requested an OTP verification code for your account. Please use the following code to complete the verification process:\n" +
                                "\n" +
                                "Your OTP code is: "+ otp.getOtpCode() + "\n" +
                                "\n" +
                                "If you did not request this verification code, please ignore this email.\n" +
                                "\n" +
                                "Best regards,"
                )
        );
        //return null;

    }


    @POST
    @Path("/otp")
    public Response verifyOTPCode(@Body OTP emailOTP) {
        System.out.println("verifyOTPCode " );
        System.out.println("getEmail = "  + emailOTP.getEmail());
        System.out.println("getOTPCode = "  + emailOTP.getOtpCode());
        OTP otp = otpService.verifyOTP(emailOTP.getEmail(), emailOTP.getOtpCode());
        if(otp != null){
            if(otp.getOtpCode().equals(emailOTP.getOtpCode())){
                // Delete the OTP from the database
                System.out.println("OTP OK " );
                otpService.deleteByEmail(emailOTP.getEmail());
                return Response.ok().build();
            }
        }
        System.out.println("OTP NOT OK " );
        return Response.status(Response.Status.BAD_REQUEST).build();

    }
}
