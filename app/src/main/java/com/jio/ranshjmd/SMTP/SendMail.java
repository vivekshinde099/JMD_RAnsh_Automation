package com.jio.ranshjmd.SMTP;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.StrictMode;
import android.util.Log;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.Security;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;

import com.jio.ranshjmd.Common.Datacontainer;
import com.jio.ranshjmd.Common.FirebaseConnectivity;
import com.jio.ranshjmd.R;
import com.jio.ranshjmd.Ticketresults.Ticket_NotResolved;
import com.jio.ranshjmd.Ticketresults.Ticket_Resolved;


public class SendMail extends Authenticator {
    private String mailhost = "smtp.live.com";
    private String user;
    private String pass;
    private Session session;
    private Multipart _multipart;
    private ProgressDialog progressDialog;

    static {
        Security.addProvider(new JSSEProvider());
    }

    private Object Context;


    public SendMail(String user, String pass) {
        this.user = user;
        this.pass = pass;

        Properties props = new Properties();
        props.setProperty("mail.transport.protocol", "smtp");
        props.setProperty("mail.smtp.host", mailhost);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.starttls.enable","true");


        session = Session.getDefaultInstance(props, this);
        _multipart = new MimeMultipart();
    }

    protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(user, pass);
    }

    public synchronized void sendMail(String subject, String body, String sender, String recipients,PopupWindow popupWindow,String tocheck,String btnumber,String remaks,Activity activity,String updtsttus ) throws Exception {
      try{

          int countresolve = 0;
            MimeMessage message = new MimeMessage(session);
            DataHandler handler = new DataHandler(new ByteArrayDataSource(body.getBytes(), "text/plain"));
            message.setSender(new InternetAddress(sender));
            message.setSubject(subject);
            message.setDataHandler(handler);
            message.setContent(_multipart);
            BodyPart myBodyPart = new MimeBodyPart();

            // Now set the actual message
            myBodyPart.setText(body);
            _multipart.addBodyPart(myBodyPart);

            if (recipients.indexOf(',') > 0)
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipients));
            else
                message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipients));

            int SDK_INT = Build.VERSION.SDK_INT;
            if (SDK_INT > 8) {
                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                        .permitAll().build();
                StrictMode.setThreadPolicy(policy);
                //your codes here
                try {
                    Transport.send(message);
                } catch (Exception e) {
                    System.out.println(e);
                    countresolve++;
                    Datacontainer.customtoast("Kindly Re-try", activity,R.drawable.ic_round_info_24);
                }

                if (countresolve==0 && tocheck=="reopen" ){
                    DatabaseReference databaseReference1 = FirebaseDatabase.getInstance().getReference().child("Bot Data");
                    databaseReference1.child(btnumber).child("remark").setValue(remaks);
                    databaseReference1.child(btnumber).child("status").setValue(updtsttus);
//                    popupWindow.dismiss();


                }else if (countresolve==0 && tocheck=="resolved"){

                    Intent intent = new Intent(activity, Ticket_Resolved.class);
                    intent.putExtra("issuename",remaks);
                    activity.startActivity(intent);
                    activity.finish();
                    popupWindow.dismiss();
                }else if (countresolve==0 && tocheck=="Severity"){
                    Datacontainer.customtoast(remaks,activity,R.drawable.check_icon);
                }
            }
            //     Transport.send(message);
        }catch(Exception e){
            Log.d("mylog", "Error in sending: " + e.toString());
        }
    }

    ///////////////////////////////////////////
    public synchronized void sendMail(String subject, String body, String sender, String recipients, String maxid, String Issuetitle, String BTTICKETID, String phonenumber, Activity activity,PopupWindow popupWindow,String PRM) throws Exception
    {


        if (subject.isEmpty() || body.isEmpty() || sender.isEmpty() || recipients.isEmpty() || maxid.isEmpty() || BTTICKETID.isEmpty() || phonenumber.isEmpty()){

            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(activity, "Please Check network Connection, Retry", Toast.LENGTH_LONG).show();
                }
            });
        }
        else {
            try {
                int count = 0;
                int max = Integer.parseInt(maxid);

                MimeMessage message = new MimeMessage(session);
                DataHandler handler = new DataHandler(new ByteArrayDataSource(body.getBytes(), "text/plain"));
                message.setSender(new InternetAddress(sender));
                message.setSubject(subject);
                message.setDataHandler(handler);
                message.setContent(_multipart);
                BodyPart myBodyPart = new MimeBodyPart();

                // Now set the actual message
                myBodyPart.setText(body);
                _multipart.addBodyPart(myBodyPart);

                if (recipients.indexOf(',') > 0)
                    message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipients));
                else
                    message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipients));

                int SDK_INT = Build.VERSION.SDK_INT;
                if (SDK_INT > 8) {
                    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                            .permitAll().build();
                    StrictMode.setThreadPolicy(policy);
                    //your codes here
                    try {
                        Transport.send(message);
                    } catch (Exception e) {
                        System.out.println(e);
                        count++;
                        Datacontainer.mailsender = "false";
                        activity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Datacontainer.customtoast("Due to Network Fluctuation unable to raise your concern. Kindly Retry",activity,R.drawable.ic_round_info_24);
                            }
                        });

                    }

                    if (count==0){
                        max=max-1;
                        String id= String.valueOf(max+1);

                        FirebaseConnectivity.updatebotdata(BTTICKETID,Issuetitle,getDate(),id,phonenumber,activity,PRM);

                       if (FirebaseConnectivity.createticket == "b") {
                           Intent intent = new Intent(activity, Ticket_NotResolved.class);
                           intent.putExtra("Ticket_no", BTTICKETID);
                           intent.putExtra("Title",Issuetitle);
                           intent.putExtra("Mobilenumber",phonenumber);
                           intent.putExtra("Resolved", "notresolved");
                           activity.startActivity(intent);
                           activity.finish();
                           popupWindow.dismiss();
                       }

                    }
                }

                //     Transport.send(message);
            }
            catch(Exception e){

                Log.d("mylog", "Error in sending: " + e.toString());
            }
        }

    }



    public void addAttachment(String filename) throws Exception
    {
        try {

            MimeBodyPart messageBodyPart = new MimeBodyPart();
            DataSource source = new FileDataSource(filename);
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName(filename);
            messageBodyPart.attachFile(filename);
            //     messageBodyPart.setText(body);
            _multipart.addBodyPart(messageBodyPart);
        }catch (Exception e){
            System.out.println(e);
        }



    }



    public static class ByteArrayDataSource implements DataSource {
        private byte[] data;
        private String type;

        public ByteArrayDataSource(byte[] data, String type) {
            super();
            this.data = data;
            this.type = type;
        }

        public ByteArrayDataSource(byte[] data) {
            super();
            this.data = data;
        }



        public void setType(String type) {
            this.type = type;
        }

        public String getContentType() {
            if (type == null)
                return "application/octet-stream";
            else
                return type;
        }

        public InputStream getInputStream() throws IOException {
            return new ByteArrayInputStream(data);
        }

        public String getName() {
            return "ByteArrayDataSource";
        }

        public OutputStream getOutputStream() throws IOException {
            throw new IOException("Not Supported");
        }
    }

    private String getDate(){
        DateFormat dfDate = new SimpleDateFormat("yyyy/MM/dd");
        String date=dfDate.format(Calendar.getInstance().getTime());
        DateFormat dfTime = new SimpleDateFormat("HH:mm");
        String time = dfTime.format(Calendar.getInstance().getTime());
        return date + " " + time;
    }






}
