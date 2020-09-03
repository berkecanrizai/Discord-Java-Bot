package com.github.berkecanrizai.myfirstbot;
import  java.util.*;
import java.net.MalformedURLException;
import java.net.URL;

import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.entity.message.Reaction;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.awt.*;
import java.util.*;
//Author: Berke Can Rizai 
//mail: berkecanrizai1@gmail.com
public class Main {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		int d=0;
		DiscordApi api = new DiscordApiBuilder().setToken("NzAxMDU4ODcyNTU0ODE1NDkw.XpsAlQ.GcCxfph0zKTOm4U-jv7E5G6wWMc").login().join();
        System.out.println("Logged in!");
        
        api.addMessageCreateListener(event -> {
        	//greet user with their name and list the available commands
            if (event.getMessageContent().equalsIgnoreCase(".canbot")) {
            	String name= event.getMessage().getAuthor().getDisplayName().toString();
                event.getChannel().sendMessage("efendim "+name+" bey ");
                event.getChannel().sendMessage("yardim icin .help yaz");
                event.getChannel().sendMessage("version: v0.1 alpha\ncanbot™.");
            }
            //see below playsomething command
            else if (event.getMessageContent().equalsIgnoreCase(".playSomething")) {
                event.getChannel().sendMessage(playSomething());
                event.getChannel().sendMessage("Simdilik tek parca var ileride yenilerini ekleyebilirsin adamim");
            }
            else if (event.getMessageContent().equalsIgnoreCase(".sil")) {
                event.getChannel().sendMessage(".sonMesajlariTemizle <mesaj sayisi> seklinde yaziniz sayi 2 ile 10 arasi olsun");
            }
            //delete last messages to cleanup the chat
            else if (event.getMessageContent().matches("\\.sil\\s\\d+")) {
            	
            	int count =Integer.valueOf(event.getMessageContent().toString().replaceAll("\\D+",""));
            	if(count>10||!event.getMessageAuthor().canManageMessagesInTextChannel()) event.getChannel().sendMessage("2 ile 10 arasi olsun ve kanal yetkisi gerekli");
            	else {	
            		event.getChannel().getMessagesAsStream().limit(count).forEach(item->item.delete());
            	}
            }
            else if (event.getMessageContent().equalsIgnoreCase(".help")) {
                event.getChannel().sendMessage(printCommands());
            }
            else if (event.getMessageContent().equalsIgnoreCase(".wiki")) {
                event.getChannel().sendMessage(getRandomPage());
            }
           /* else if (event.getMessageContent().equalsIgnoreCase(":D")) {
                api.getServerVoiceChannelMemberJoinListeners().size()
                
            }*/
            //simple dice roll
            else if (event.getMessageContent().matches("\\.roll\\s\\d+")) {
            	int count =Integer.valueOf(event.getMessageContent().toString().replaceAll("\\D+",""));
            	Random ren=new Random();
            	int number=(ren.nextInt(count-1)+1);
            	 event.getChannel().sendMessage("Dice: "+ number);
            }
            else if (event.getMessageContent().matches("\\.herkesemutluluk\\s\\d+")) {
            	//supposedly makes everyone happy by giving nice emojis to last x count messages
            	int count =Integer.valueOf(event.getMessageContent().toString().replaceAll("\\D+",""));
            	if(count>20) event.getChannel().sendMessage("20 den kucuk olsun");
            	else {	
            	//event.getChannel().getMessagesAsStream().limit(count).forEach(item->item.));
            		for(int i=0;i<Reaction.class.getClass().getMethods().length;i++)
            		System.out.println(Reaction.class.getClass().getMethods()[i]);
            	}
            }
            
           /* else if (event.getMessageContent().toString().equalsIgnoreCase("@canbot")) {
            	String name= event.getMessage().getAuthor().toString();
                event.getChannel().sendMessage("efendim "+name+" bey ");
            }
            */
            
            
            
            
            
       ////////////////////////////////     
        });
        api.addServerVoiceChannelMemberJoinListener(event -> {
        	if(true)event.getChannel().createUpdater();
        	if(true) event.getChannel().getConnectedUsers().toString();
        });
        //api.getServerTextChannels().parallelStream().forEach(event->event.sendMessage(event.user));
        
        
        
        
	}
	//supposedly will make other discord music bots play music but since bots usually don't read other bots messages 
	//it won't work
	//one solution would be giving bot admin priviliges maybe so it could send commands but not sure how it would work
	public static String playSomething() {
		return "!p https://www.youtube.com/watch?v=xhltUNCho6U";
		
	}
	public static String printCommands() {
		return "Mevcut commandler:\n.sil \n.muzigiSustur\n.playSomething\n.canbot\n.zarat <sayi>\n.wiki";
	}
	
	
	
	// internet webpage fetcher, however needs work 
	public static String getRandomPage(){
		URL url=null;
		
		try {
			url = new URL("https://www.trtworld.com/");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		URLConnection con=null;
		try {
			con = url.openConnection();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		InputStream is=null;
        try {
			is =con.getInputStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        String line = null;
        String message=null;
        boolean CodeNeeded = false;

        try {
			while ((line = br.readLine()) != null) {
			    // Here I Point on the beginig of the Code needed
			    if(line.contains("<div class=\"zg_rank\">")){
			        CodeNeeded = true;
			    }

			    // Here I Point on the End of the Code needed
			    if (line.contains("</div>")) {
			        CodeNeeded = false;
			    }        
			    // If the Code is needed Stored it in DivWanted
			    if(CodeNeeded) {
			        message += line + "\n";
			    }    
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
		return message;
	}
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
