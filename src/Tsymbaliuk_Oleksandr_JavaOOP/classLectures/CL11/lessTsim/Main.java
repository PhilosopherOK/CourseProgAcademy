package Tsymbaliuk_Oleksandr_JavaOOP.classLectures.CL11.lessTsim;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String html = "<!doctype html>\n<html>\n <head>\n<title>This is the sample webpage!</title>\n</head>\n"
				+ "<body>\nJava the best\n</body>\n</html>";
		
		try (ServerSocket serSocket = new ServerSocket(8080)) {
			for (;;) {
				Socket socket = serSocket.accept();
				NetworkService ns = new NetworkService(socket, html);
				Thread thr = new Thread(ns);
				thr.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
