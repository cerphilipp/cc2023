package cc2023.teamrandom.ccservice.model;

class MastodonApplication {
	private String name;
	private Object website;
	private String vapidKey;
	private Object clientId;
	private Object clientSecret;

	// Getter und Setter hier einfügen

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Object getWebsite() {
		return website;
	}

	public void setWebsite(Object website) {
		this.website = website;
	}

	public String getVapidKey() {
		return vapidKey;
	}

	public void setVapidKey(String vapidKey) {
		this.vapidKey = vapidKey;
	}

	public Object getClientId() {
		return clientId;
	}

	public void setClientId(Object clientId) {
		this.clientId = clientId;
	}

	public Object getClientSecret() {
		return clientSecret;
	}

	public void setClientSecret(Object clientSecret) {
		this.clientSecret = clientSecret;
	}
}
