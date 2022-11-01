package com.lsr.service.register;

import java.time.LocalDateTime;

import javax.transaction.Transactional;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
//import org.hibernate.validator.internal.constraintvalidators.bv.EmailValidator;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import lombok.AllArgsConstructor;

import com.lsr.entity.register.token.ConfirmationToken;
import com.lsr.entity.register.token.ConfirmationTokenService;
import com.lsr.request.register.RegistrationRequest;
import com.lsr.response.LoginResponse;
import com.lsr.response.RegiterResponse;
import com.lsr.service.user.*;
import com.lsr.entity.user.*;
import com.lsr.entity.email.*;
import com.lsr.service.email.*;
@Service
@AllArgsConstructor
public class RegistrationService {
	
	private final UserDetailsServiceImpl appUserService;
	private final EmailValidator emailValidator;
    private final ConfirmationTokenService confirmationTokenService;
    private final EmailSender emailSender;
    
     @SuppressWarnings({ "rawtypes" })
     public RegiterResponse register(RegistrationRequest request) {
		RegiterResponse responseApi = new RegiterResponse();
		boolean isValidEmail = emailValidator.
                test(request.getEmail());
		
		if (!isValidEmail) {
			responseApi.setStatus("Error");
			responseApi.setMessage("email not valid");
        }
		else {
			try { 
				
				responseApi = appUserService.signUpUser(

		        		new User(
		                        request.getUsername(),
		                        request.getPassword(),
		                        request.getEmail(),
		                        request.getFullname()
		                )
				);
			 
				if (responseApi.getToken() !="Error") {
//					final String url= UriComponentsBuilder.fromHttpUrl(baseURL)
//			                .path(“/register/verify”).queryParam("token", token).toUriString();
			        
					String link = "http://localhost:9040/api/lsr_enhance/main/v1/confirm?token=" + responseApi.getToken() +"&username=" + request.getUsername();
			        
					emailSender.send(
				                request.getEmail(),
				                buildEmail(request.getFullname(), link));
					responseApi.setStatus("Success");
				}
				
			 }
			 catch(Exception e)
			 {
				 responseApi.setStatus("Error");
				 responseApi.setMessage(e.getMessage());
			 }
			 
		}
		
	   	return responseApi;
	 }
    
    
//    public String register(RegistrationRequest request) {
//        boolean isValidEmail = emailValidator.
//                test(request.getEmail());
//
//        if (!isValidEmail) {
//            throw new IllegalStateException("email not valid");
//        }
//
//        String token = appUserService.signUpUser(
//
//        		new User(
//                        request.getUsername(),
//                        request.getPassword(),
//                        request.getEmail(),
//                        request.getFullname()
//                )
//        );
//
//        String link = "http://localhost:9040/api/lsr_enhance/main/v1/confirm?token=" + token +"&username=" + request.getUsername();
//        emailSender.send(
//                request.getEmail(),
//                buildEmail(request.getFullname(), link));
//
//        return token;
//    }
    

    @Transactional
    @SuppressWarnings({ "rawtypes" })
    public RegiterResponse confirmToken(String token, String username) {
    	RegiterResponse responseApi = new RegiterResponse();
    	
    	try {
    		ConfirmationToken confirmationToken = confirmationTokenService
                    .getToken(token)
                    .orElseThrow(() ->
                            new IllegalStateException("token not found")
                    		);
    		
    		if (confirmationToken.getConfirmedAt() != null) {
            	responseApi.setStatus("Error");
    			responseApi.setMessage("email already confirmed");
                //throw new IllegalStateException("email already confirmed");
            }
            else {
            	LocalDateTime expiredAt = confirmationToken.getExpiresAt().toLocalDateTime();

                if (expiredAt.isBefore(LocalDateTime.now())) {
                	responseApi.setStatus("Error");
        			responseApi.setMessage("token expired");
//                    throw new IllegalStateException("token expired");
                }
                else {
                	 confirmationTokenService.setConfirmedAt(token,username);
                     appUserService.enableAppUser(
                             confirmationToken.getAppUser().getEmail());
                     responseApi.setToken(token);
                     responseApi.setStatus("Success");
         			 responseApi.setMessage("Email has been Confirm");
                }
            }

    	}
    	catch(Exception e) {
    		responseApi.setStatus("Error");
			responseApi.setMessage(e.getMessage());
    	}
        
        return responseApi;
    }
    
    public String buildEmail(String name, String link) {
        return "<div style=\"font-family:Helvetica,Arial,sans-serif;font-size:16px;margin:0;color:#0b0c0c\">\n" +
                "\n" +
                "<span style=\"display:none;font-size:1px;color:#fff;max-height:0\"></span>\n" +
                "\n" +
                "  <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;min-width:100%;width:100%!important\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\n" +
                "    <tbody><tr>\n" +
                "      <td width=\"100%\" height=\"53\" bgcolor=\"#0b0c0c\">\n" +
                "        \n" +
                "        <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;max-width:580px\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\">\n" +
                "          <tbody><tr>\n" +
                "            <td width=\"70\" bgcolor=\"#0b0c0c\" valign=\"middle\">\n" +
                "                <table role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
                "                  <tbody><tr>\n" +
                "                    <td style=\"padding-left:10px\">\n" +
                "                  \n" +
                "                    </td>\n" +
                "                    <td style=\"font-size:28px;line-height:1.315789474;Margin-top:4px;padding-left:10px\">\n" +
                "                      <span style=\"font-family:Helvetica,Arial,sans-serif;font-weight:700;color:#ffffff;text-decoration:none;vertical-align:top;display:inline-block\">Confirm your email</span>\n" +
                "                    </td>\n" +
                "                  </tr>\n" +
                "                </tbody></table>\n" +
                "              </a>\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "        </tbody></table>\n" +
                "        \n" +
                "      </td>\n" +
                "    </tr>\n" +
                "  </tbody></table>\n" +
                "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
                "    <tbody><tr>\n" +
                "      <td width=\"10\" height=\"10\" valign=\"middle\"></td>\n" +
                "      <td>\n" +
                "        \n" +
                "                <table role=\"presentation\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
                "                  <tbody><tr>\n" +
                "                    <td bgcolor=\"#1D70B8\" width=\"100%\" height=\"10\"></td>\n" +
                "                  </tr>\n" +
                "                </tbody></table>\n" +
                "        \n" +
                "      </td>\n" +
                "      <td width=\"10\" valign=\"middle\" height=\"10\"></td>\n" +
                "    </tr>\n" +
                "  </tbody></table>\n" +
                "\n" +
                "\n" +
                "\n" +
                "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
                "    <tbody><tr>\n" +
                "      <td height=\"30\"><br></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
                "      <td style=\"font-family:Helvetica,Arial,sans-serif;font-size:19px;line-height:1.315789474;max-width:560px\">\n" +
                "        \n" +
                "            <p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\">Hi " + name + ",</p><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> Thank you for registering. Please click on the below link to activate your account: </p><blockquote style=\"Margin:0 0 20px 0;border-left:10px solid #b1b4b6;padding:15px 0 0.1px 15px;font-size:19px;line-height:25px\"><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> <a href=\"" + link + "\">Activate Now</a> </p></blockquote>\n Link will expire in 15 minutes. <p>See you soon</p>" +
                "        \n" +
                "      </td>\n" +
                "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td height=\"30\"><br></td>\n" +
                "    </tr>\n" +
                "  </tbody></table><div class=\"yj6qo\"></div><div class=\"adL\">\n" +
                "\n" +
                "</div></div>";
    }

}
