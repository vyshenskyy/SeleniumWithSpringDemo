package sample.application.multitenant.pages;

public enum MtTenant {
	FIRST ("first"),
	SECOND ("second");
	
	private final String value;
	MtTenant(String value) {
		this.value = value;
	}
	
	public String getValue() {return value;}
	
	public static MtTenant fromName(String tenantName) {
		for(MtTenant tenant : values()) {
			if(tenant.value.equalsIgnoreCase(tenantName)) {
				return tenant;
			}			
		}
		throw new IllegalArgumentException("Wrong tenant name: " + tenantName);
	}
}
