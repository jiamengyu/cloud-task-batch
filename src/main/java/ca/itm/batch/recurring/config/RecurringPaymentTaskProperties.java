package ca.itm.batch.recurring.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.util.Assert;

@ConfigurationProperties
public class RecurringPaymentTaskProperties {

	/**
	 * The timestamp format, "yyyy-MM-dd HH:mm:ss.SSS" by default.
	 */
	private String format = "yyyy-MM-dd HH:mm:ss.SSS";
	private String testFlag = "false";
	private String paymentInstanceProviderUrl = "api.endpoint.com/v1/api";
	
	public String getPaymentInstanceProviderUrl() {
		Assert.hasText(paymentInstanceProviderUrl, "paymentInstanceProviderUrl must not be empty nor null");
		return paymentInstanceProviderUrl;
	}

	public void setPaymentInstanceProviderUrl(String paymentInstanceProviderUrl) {
		this.paymentInstanceProviderUrl = paymentInstanceProviderUrl;
	}

	public String getTestFlag() {
		return testFlag;
	}

	public void setTestFlag(String testFlag) {
		this.testFlag = testFlag;
	}

	public String getFormat() {
		Assert.hasText(format, "Format must not be empty nor null");
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}
}