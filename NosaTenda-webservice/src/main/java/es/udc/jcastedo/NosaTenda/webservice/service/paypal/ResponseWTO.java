package es.udc.jcastedo.NosaTenda.webservice.service.paypal;

import java.util.Calendar;

public class ResponseWTO {

	private String state;
	private String id;
	private Calendar create_time;
	private String intent;

	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Calendar getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Calendar create_time) {
		this.create_time = create_time;
	}
	public String getIntent() {
		return intent;
	}
	public void setIntent(String intent) {
		this.intent = intent;
	}
	
	
}
