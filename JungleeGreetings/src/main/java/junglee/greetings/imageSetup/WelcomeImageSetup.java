package junglee.greetings.imageSetup;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import junglee.greetings.mail.SendMail;
import junglee.greetings.model.UserFormData;
import junglee.greetings.repository.UserFormDataRepository;
import junglee.greetings.security.CustomUserDetailsService;

@Component
public class WelcomeImageSetup {
	
	@Autowired
    private SendMail sendMail;
	
	@Autowired
	private UserFormDataRepository userdata;
	
	public void overlayImagesMutipleNewJoinee(List<UserFormData> newjoinee) {
		   
	    int n = newjoinee.size();

	    String name[] = new String[n];
	    BufferedImage fgImage[] = new BufferedImage[n];
	    String emailSubject = "Welcome our new Junglee";
		ArrayList<String> cc = new ArrayList<>();
		ArrayList<String> emails = new ArrayList<>();
	    
	    int index = 0;
	    while(newjoinee.size() > 0) {
	    	name[index] = newjoinee.get(0).getFullname();
	    	fgImage[index] = readImage("src/main/resources/static/image/"+newjoinee.get(0).getEmail()+"photo.jpg");
	    	cc.add(newjoinee.get(0).getEmail());
	    	emails.add(newjoinee.get(0).getEmail());
	    	index++;
	    	newjoinee.remove(0);
	    }
	    BufferedImage bgImageTitle = readImage("imageTemplates/new_joinee_upper.png");
	    BufferedImage bgImageBase = readImage("imageTemplates/new_joinee_base.png");
	    BufferedImage bgImageEnd = readImage("imageTemplates/new_joinee_lower.png");
	   
	    int baseNumber=n/2+n%2;
	   
	 
	    BufferedImage bufferedImage = new BufferedImage(bgImageBase.getWidth(), bgImageTitle.getHeight()+baseNumber*bgImageBase.getHeight()+bgImageEnd.getHeight(), BufferedImage.TYPE_INT_RGB);
	   
	         Graphics2D g = bufferedImage.createGraphics();
	     
	        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
	                RenderingHints.VALUE_ANTIALIAS_ON);    
	   
	        g.drawImage(bgImageTitle, 0, 0, null);
	       
	        String email="kashish@jungleegames.com";
	        String Experience="Experience:2 years";
	        String Last_org="Last Organization: Zomato";
	        String work="Joins as:Customer Care Exicutive (Gurgaon)";
	        String about="Hails From:The function returns maximum of two numbers. The datatype will be the same as that of the arguments.The function returns maximum of two numbers. The datatype will be the same as that of the arguments.";
	        int j=0;
	        for(int i=0;i<baseNumber;i++) {
	        g.drawImage(bgImageBase, 0, bgImageTitle.getHeight()+i*bgImageBase.getHeight(), null);
	        if(n==1) {
	        g.setColor(Color.white);
	                g.fillRect(bgImageBase.getWidth()/2-175, bgImageTitle.getHeight()+i*bgImageBase.getHeight()+bgImageBase.getHeight()/2-300,350,600);
	        int[] a=resizeNewJoinee(fgImage[j]);
	        g.drawImage(fgImage[j], bgImageBase.getWidth()/2-a[0]/2, bgImageTitle.getHeight()+i*bgImageBase.getHeight()+50,a[0],a[1], null);
	        g.setFont(new Font("Serif", Font.BOLD, 25));
	        g.setColor(Color.red);
	        g.drawString(name[j], bgImageBase.getWidth()/2-name[j].length()/2*16, bgImageTitle.getHeight()+i*bgImageBase.getHeight()+a[1]+80);
	        g.setFont(new Font("Serif", Font.PLAIN, 20));
	        g.setColor(Color.blue);
	        g.drawString(email, bgImageBase.getWidth()/2-email.length()/2*11, bgImageTitle.getHeight()+i*bgImageBase.getHeight()+a[1]+105);
	        g.setColor(Color.black);
	        g.drawString(Experience, bgImageBase.getWidth()/2-Experience.length()/2*11, bgImageTitle.getHeight()+i*bgImageBase.getHeight()+a[1]+130);
	        g.drawString(Last_org, bgImageBase.getWidth()/2-Last_org.length()/2*11, bgImageTitle.getHeight()+i*bgImageBase.getHeight()+a[1]+155);
	        ArrayList<String> abc=stringdivideJoinee(work);
	        for(int k=0;k<abc.size();k++) {
	            g.drawString(abc.get(k), bgImageBase.getWidth()/2-abc.get(k).length()/2*11, bgImageTitle.getHeight()+i*bgImageBase.getHeight()+a[1]+180+k*25);
	        }
	        ArrayList<String> abcd=stringdivideJoinee(about);
	        for(int k=0;k<abcd.size();k++) {
	            g.drawString(abcd.get(k), bgImageBase.getWidth()/2-abcd.get(k).length()/2*11, bgImageTitle.getHeight()+i*bgImageBase.getHeight()+a[1]+180+k*25+abc.size()*25);
	        }
	        j++;
	        }else {
	        g.setColor(Color.white);
	                g.fillRect(bgImageBase.getWidth()/4-175, bgImageTitle.getHeight()+i*bgImageBase.getHeight()+bgImageBase.getHeight()/2-300,350,600);
	        int[] a=resizeNewJoinee(fgImage[j]);
	        g.drawImage(fgImage[j], bgImageBase.getWidth()/4-a[0]/2, bgImageTitle.getHeight()+i*bgImageBase.getHeight()+50,a[0],a[1], null);
	        g.setFont(new Font("Serif", Font.BOLD, 25));
	        g.setColor(Color.red);
	        g.drawString(name[j], bgImageBase.getWidth()/4-name[j].length()/2*16, bgImageTitle.getHeight()+i*bgImageBase.getHeight()+a[1]+80);
	        g.setFont(new Font("Serif", Font.PLAIN, 20));
	        g.setColor(Color.blue);
	        g.drawString(email, bgImageBase.getWidth()/4-email.length()/2*11, bgImageTitle.getHeight()+i*bgImageBase.getHeight()+a[1]+105);
	        g.setColor(Color.black);
	        g.drawString(Experience, bgImageBase.getWidth()/4-Experience.length()/2*11, bgImageTitle.getHeight()+i*bgImageBase.getHeight()+a[1]+130);
	        g.drawString(Last_org, bgImageBase.getWidth()/4-Last_org.length()/2*11, bgImageTitle.getHeight()+i*bgImageBase.getHeight()+a[1]+155);
	        ArrayList<String> abc=stringdivideJoinee(work);
	        for(int k=0;k<abc.size();k++) {
	            g.drawString(abc.get(k), bgImageBase.getWidth()/4-abc.get(k).length()/2*11, bgImageTitle.getHeight()+i*bgImageBase.getHeight()+a[1]+180+k*25);
	        }
	        ArrayList<String> abcd=stringdivideJoinee(about);
	        for(int k=0;k<abcd.size();k++) {
	            g.drawString(abcd.get(k), bgImageBase.getWidth()/4-abcd.get(k).length()/2*11, bgImageTitle.getHeight()+i*bgImageBase.getHeight()+a[1]+180+k*25+abc.size()*25);
	        }
	        j++;
	        g.setColor(Color.white);
	                g.fillRect(3*bgImageBase.getWidth()/4-175, bgImageTitle.getHeight()+i*bgImageBase.getHeight()+bgImageBase.getHeight()/2-300,350,600);
	        a=resizeNewJoinee(fgImage[j]);
	        g.drawImage(fgImage[j], 3*bgImageBase.getWidth()/4-a[0]/2, bgImageTitle.getHeight()+i*bgImageBase.getHeight()+50,a[0],a[1], null);
	        g.setFont(new Font("Serif", Font.BOLD, 25));
	        g.setColor(Color.red);
	        g.drawString(name[j], 3*bgImageBase.getWidth()/4-name[j].length()/2*16, bgImageTitle.getHeight()+i*bgImageBase.getHeight()+a[1]+80);
	        g.setFont(new Font("Serif", Font.PLAIN, 20));
	        g.setColor(Color.blue);
	        g.drawString(email, 3*bgImageBase.getWidth()/4-email.length()/2*11, bgImageTitle.getHeight()+i*bgImageBase.getHeight()+a[1]+105);
	        g.setColor(Color.black);
	        g.drawString(Experience, 3*bgImageBase.getWidth()/4-Experience.length()/2*11, bgImageTitle.getHeight()+i*bgImageBase.getHeight()+a[1]+130);
	        g.drawString(Last_org, 3*bgImageBase.getWidth()/4-Last_org.length()/2*11, bgImageTitle.getHeight()+i*bgImageBase.getHeight()+a[1]+155);
	        abc=stringdivideJoinee(work);
	        for(int k=0;k<abc.size();k++) {
	            g.drawString(abc.get(k), 3*bgImageBase.getWidth()/4-abc.get(k).length()/2*11, bgImageTitle.getHeight()+i*bgImageBase.getHeight()+a[1]+180+k*25);
	        }
	        abcd=stringdivideJoinee(about);
	        for(int k=0;k<abcd.size();k++) {
	            g.drawString(abcd.get(k), 3*bgImageBase.getWidth()/4-abcd.get(k).length()/2*11, bgImageTitle.getHeight()+i*bgImageBase.getHeight()+a[1]+180+k*25+abc.size()*25);
	        }
	        j++;
	        n-=2;
	        }
	        }
	       
	        g.drawImage(bgImageEnd, 0, bgImageTitle.getHeight()+baseNumber*bgImageBase.getHeight(), null);
	        g.dispose();
	
	        //writeImage(bufferedImage, "src/main/resources/static/GreetingImages/welcomegreeting.jpg", "jpg");
	        //System.out.println("image saved in local folder");
	        
	        File outputfile = bufferedImageToFile(bufferedImage);
	        sendMail.sendWithAttachement("rohitsalwan2020@gmail.com","pram_be16@thapar.edu", cc, emailSubject, outputfile);
	        /*
	         * set welcomemail to true means email has send for new user
	         * and no need to send the mail again
	         */
	        while(emails.size() > 0) {
	        	UserFormData newuser = userdata.findByEmail(emails.get(0));
	        	newuser.setWelcomemail(true);
	        	userdata.save(newuser);
	        	emails.remove(0);
	        }
	}
	
//	private void writeImage(BufferedImage img, String fileLocation, String extension) {
//        try {
//            BufferedImage bi = img;
//            File outputfile = new File(fileLocation);
//            ImageIO.write(bi, extension, outputfile);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

	private File bufferedImageToFile(BufferedImage bufferedImage) {
		File outputfile = new File("welcomeGreeting.jpg");
		try {
			ImageIO.write(bufferedImage, "jpg", outputfile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return outputfile;
	}

	private static ArrayList<String> stringdivideJoinee(String a) {
		ArrayList<String> ans=new ArrayList<String>();
	    int start=0;
	    // int pointer=0;
	    int spacepointer=0;
	    for(int i=0;i<a.length();i++) {
	    if(i-start>=31 ) {
	    if(a.charAt(i)==' ') {
	    ans.add(a.substring(start, i));
	    start=i;
	        spacepointer=i;
	        }else {
	        ans.add(a.substring(start, spacepointer));
	        start=spacepointer+1;
	        }
	    }else if(i==a.length()-1){
	    ans.add(a.substring(start, a.length()));
	    }else{
	    if(a.charAt(i)==' ') {
	        spacepointer=i;
	        }
	    }
	    }
	    return ans;
	}

	private static int[] resizeNewJoinee(BufferedImage img) {
		int a=Math.max(img.getHeight(), img.getWidth());  
	    int[] ndimention=new int[2];
	    if(a==img.getHeight() && a>200){
	    ndimention[0]=(200*img.getWidth())/img.getHeight();
	    ndimention[1]=200;
	   
	    }else if(a==img.getWidth() && a>300) {
	    ndimention[0]=300;
	    ndimention[1]=(300*img.getHeight())/img.getWidth();
	    }else {
	    ndimention[0]=img.getWidth();
	    ndimention[1]=img.getHeight();
	    }
	    return ndimention;
	}

	private static BufferedImage readImage(String fileLocation) {
		BufferedImage img = null;
        try {
            img = ImageIO.read(new File(fileLocation));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return img;
	}
	

}
