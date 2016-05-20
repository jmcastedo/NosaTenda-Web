package es.udc.jcastedo.NosaTenda.webservice.service.paypal;


public class PaypalPaymentWTO {

	private Double amount;
	private String short_description;
	private DetailsWTO details;
	private String intent;
	private String currency_code;
	private Item_listWTO item_list;
	
	public String getShort_description() {
		return short_description;
	}
	public void setShort_description(String short_description) {
		this.short_description = short_description;
	}
	public DetailsWTO getDetails() {
		return details;
	}
	public void setDetails(DetailsWTO details) {
		this.details = details;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public String getIntent() {
		return intent;
	}
	public void setIntent(String intent) {
		this.intent = intent;
	}
	public String getCurrency_code() {
		return currency_code;
	}
	public void setCurrency_code(String currency_code) {
		this.currency_code = currency_code;
	}
	public Item_listWTO getItem_list() {
		return item_list;
	}
	public void setItem_list(Item_listWTO item_list) {
		this.item_list = item_list;
	}

}
