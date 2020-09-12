package evgenyt.dirsfiles;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.DecimalFormat;

@SpringBootApplication
public class DirsfilesApplication {

	public static String strSize(long size) {
		DecimalFormat df = new DecimalFormat("0.00");
		float sizeKb = 1024.0f;
		float sizeMb = sizeKb * sizeKb;
		float sizeGb = sizeMb * sizeKb;
		if (size < sizeKb)
			return size + " b";
		else if (size < sizeMb)
			return df.format(size / sizeKb)+ " Kb";
		else if (size < sizeGb)
			return df.format(size / sizeMb) + " Mb";
		return df.format(size / sizeGb) + " Gb";
	}

	public static void main(String[] args) {
		SpringApplication.run(DirsfilesApplication.class, args);
	}

}
