import java.text.DecimalFormat;

public class FormatUtil {

	public String format(double num) {
		DecimalFormat f = new DecimalFormat("0.00");
		DecimalFormat fff = new DecimalFormat("0");
//		System.out.println(num);
		if (num < 1000 && num >= 0) {
			return fff.format(num) + "";
		}
		if (num >= 1000 && num < 999999) {
			return f.format((num / 1000)) + " thousand";
		}
		if (num >= 100000 && num < Math.pow(10, 9) - 1) {
			return f.format((num / 1000000)) + " million";
		}
		if (num >= Math.pow(10, 9) && num < Math.pow(10, 12) - 1) {
			return f.format((num / 1000000000)) + " billion";
		}
		if (num >= Math.pow(10, 12) && num < Math.pow(10, 15) - 1) {
			return f.format((num / Math.pow(10, 12))) + " trillion";
		}
		if (num >= Math.pow(10, 15) && num < Math.pow(10, 18) - 1) {
			return f.format((num / Math.pow(10, 15))) + " quadrillion";
		}
		if (num >= Math.pow(10, 19) && num < Math.pow(10, 21) - 1) {
			return f.format((num / Math.pow(10, 19))) + " quintillion";
		}
		if (num >= Math.pow(10, 22) && num < Math.pow(10, 24) - 1) {
			return f.format((num / Math.pow(10, 22))) + " sextillion";
		}
		if (num >= Math.pow(10, 25) && num < Math.pow(10, 27) - 1) {
			return f.format((num / Math.pow(10, 25))) + " septillion";
		}
		if (num >= Math.pow(10, 28) && num < Math.pow(10, 30) - 1) {
			return f.format((num / Math.pow(10, 28))) + " octillion";
		}
		else {
			return "w... what? you win";
		}

	}
}
