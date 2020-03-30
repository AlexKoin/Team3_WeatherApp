package team3.dbmanagement;

public class Result {
	private String apiAris;
	private String apiCc;
	private String apiDarkSky;
	private String apiOwm;
	private String apiWapi;
	private String apiWb;
	private String apiWs;

	public Result() {
		this("", "", "", "", "", "", "");
	}

	public Result(String apiAris, String apiCc, String apiDarkSky, String apiOwm, String apiWapi, String apiWb, String apiWs) {
		this.apiAris = apiAris;
		this.apiCc = apiCc;
		this.apiDarkSky = apiDarkSky;
		this.apiOwm = apiOwm;
		this.apiWapi = apiWapi;
		this.apiWb = apiWb;
		this.apiWs = apiWs;
	}
}
