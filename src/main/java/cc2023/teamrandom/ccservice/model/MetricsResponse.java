package cc2023.teamrandom.ccservice.model;

import java.util.List;

public class MetricsResponse {

	private String name;
	private String description;
	private String baseUnit;
	private List<Measurement> measurements;
	private List<AvailableTag> availableTags;

	// Getters and setters
	// ...


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getBaseUnit() {
		return baseUnit;
	}

	public void setBaseUnit(String baseUnit) {
		this.baseUnit = baseUnit;
	}

	public List<Measurement> getMeasurements() {
		return measurements;
	}

	public void setMeasurements(List<Measurement> measurements) {
		this.measurements = measurements;
	}

	public List<AvailableTag> getAvailableTags() {
		return availableTags;
	}

	public void setAvailableTags(List<AvailableTag> availableTags) {
		this.availableTags = availableTags;
	}

	public static class Measurement {
		private String statistic;
		private double value;

		// Getters and setters
		// ...

		public String getStatistic() {
			return statistic;
		}

		public void setStatistic(String statistic) {
			this.statistic = statistic;
		}

		public double getValue() {
			return value;
		}

		public void setValue(double value) {
			this.value = value;
		}
	}

	public static class AvailableTag {
		private String tag;
		private List<String> values;

		// Getters and setters
		// ...

		public String getTag() {
			return tag;
		}

		public void setTag(String tag) {
			this.tag = tag;
		}

		public List<String> getValues() {
			return values;
		}

		public void setValues(List<String> values) {
			this.values = values;
		}
	}

	// Additional getters and setters if needed
	// ...
}