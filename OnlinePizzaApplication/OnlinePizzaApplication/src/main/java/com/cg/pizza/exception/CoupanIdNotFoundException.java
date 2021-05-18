import java.util.Date;

public class CoupanIdNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private Date timestamp;
	private String coupan;
public CoupanIdNotFoundException() {
	// TODO Auto-generated constructor stub
}
public CoupanIdNotFoundException(String coupan) {
	super(coupan);
}
@Override
public String toString() {
	return "CoupanIdNotFoundException [timestamp=" + timestamp + ", coupan=" + coupan + "]";
}
}
