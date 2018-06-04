package ca.itm.batch.recurring.model;

//Dummy DTO for now.
public class PaymentInstance {

	private String paymentId;
    private String name;
    private String paymentInstruction;
    
	public String getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPaymentInstruction() {
		return paymentInstruction;
	}
	public void setPaymentInstruction(String paymentInstruction) {
		this.paymentInstruction = paymentInstruction;
	}
}
