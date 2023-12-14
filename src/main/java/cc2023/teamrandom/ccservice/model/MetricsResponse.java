package cc2023.teamrandom.ccservice.model;

public class MetricsResponse {
	public double getHomeAccessCount;
	public double troetAccessCount;
	public double rebloggedAccessCount;

	public MetricsResponse(double getHomeAccessCount, double troetAccessCount, double rebloggedAccessCount) {
		this.getHomeAccessCount = getHomeAccessCount;
		this.troetAccessCount = troetAccessCount;
		this.rebloggedAccessCount = rebloggedAccessCount;
	}
}
