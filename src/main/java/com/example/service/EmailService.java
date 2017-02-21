/*
 * =============================================================================
 * 
 *   Copyright (c) 2011-2014, The THYMELEAF team (http://www.thymeleaf.org)
 * 
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 * 
 *       http://www.apache.org/licenses/LICENSE-2.0
 * 
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 * 
 * =============================================================================
 */
package com.example.service;

import java.util.Arrays;
import java.util.Date;
import java.util.Locale;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.example.dto.User;

@Service
public class EmailService {

    private static final String THYMELEAF_LOGO_IMAGE = "mail/images/thymeleaf.png";

    private static final String PNG_MIME = "image/png";

    
    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private TemplateEngine htmlTemplateEngine;

    /* 
     * Send HTML mail (simple) 
     */
    public void sendSimpleMail1(
        final String recipientName, final String recipientEmail, final Locale locale)
        throws MessagingException {
    	
    	final MimeMessage mimeMessage = this.mailSender.createMimeMessage();
    	final MimeMessageHelper message = new MimeMessageHelper(mimeMessage,true);
    	message.setFrom("1614641451@qq.com");//1614641451@qq.com
    	message.setTo("544408511@qq.com");
    	message.setSubject("This is the message subject");
    	message.setText("This is the message body");
    	this.mailSender.send(mimeMessage);
    }

    /* 
     * Send HTML mail (simple) 
     */
    public void sendSimpleMail(
        final String recipientName, final String recipientEmail, final Locale locale)
        throws MessagingException {

        // Prepare the evaluation context
        final Context ctx = new Context(locale);
        ctx.setVariable("name", recipientName);
        ctx.setVariable("subscriptionDate", new Date());
        ctx.setVariable("hobbies", Arrays.asList("Cinema", "Sports", "Music"));

        // Prepare message using a Spring helper
        final MimeMessage mimeMessage = this.mailSender.createMimeMessage();
        final MimeMessageHelper message = new MimeMessageHelper(mimeMessage, "UTF-8");
        message.setSubject("Example HTML email (simple)");
        message.setFrom("1614641451@qq.com");
        message.setTo(recipientEmail);

        // Create the HTML body using Thymeleaf
        final String htmlContent = this.htmlTemplateEngine.process("email-simple", ctx);
        System.out.println(htmlContent);
        message.setText(htmlContent, true /* isHtml */);

        // Send email
        this.mailSender.send(mimeMessage);
    	
    }

    
   

    
    public void sendClientMail(User user)
            throws Exception {
        	
            
        	try {
                final Context ctx = new Context();
                ctx.setVariable("reback", user);
                final String htmlContent = this.htmlTemplateEngine.process("sent", ctx);
                
                // Prepare message using a Spring helper
                final MimeMessage mimeMessage = this.mailSender.createMimeMessage();
                final MimeMessageHelper message
                    = new MimeMessageHelper(mimeMessage, true /* multipart */, "UTF-8");
                
                message.setSubject("subject");
               
                message.setFrom("1614641451@qq.com");
                message.setTo("544408511@qq.com");
                message.setText(htmlContent,true);
                
             
                message.addInline("coroporate-care", new ClassPathResource(THYMELEAF_LOGO_IMAGE), PNG_MIME);
                // Send email
                this.mailSender.send(mimeMessage);
            
    		} catch (Exception e) {
    		}
       
        	
        }
}
