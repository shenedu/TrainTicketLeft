package com.butterfly.train;

public class TrainInfo {

	public static TrainInfo valueOf(String str) {
		String[] parts = str.split(",");

		if (parts.length != 18) {
			TrainInfo i = new TrainInfo();

			String trainInfo = parts[1];
			String[] tmp = trainInfo.split("\\^");
			ValueUrlPair pair = new ValueUrlPair(tmp[0], tmp[1]);
			i.trainNo = pair;

			String startStation = parts[2];
			tmp = startStation.split("\\^");
			pair = new ValueUrlPair(tmp[0], tmp[1]);
			i.startStation = pair;

			String arriveStation = parts[3];
			tmp = arriveStation.split("\\^");
			pair = new ValueUrlPair(tmp[0], tmp[1]);
			i.arriveStation = pair;

			i.startTime = parts[4];
			i.arriveTime = parts[5];
			i.lastTime = parts[6];
			i.hardSeat = parts[7];
			i.softSeat = parts[8];
			i.hardSleep = parts[9];
			i.softSeat = parts[10];
			return i;
		}
		return null;
	}

	public static class ValueUrlPair {

		public ValueUrlPair(String value, String url) {

			this.value = value;
			this.url = url;
		}

		@Override
		public String toString() {

			return value + "\t" + url;
		}

		String value;
		String url;
	}

	String day;
	String month;
	ValueUrlPair startStation;
	ValueUrlPair arriveStation;
	ValueUrlPair trainNo;
	String startTime;
	String arriveTime;
	String lastTime;
	String hardSeat;
	String softSeat;
	String hardSleep;
	String softSleep;

	
// public static String getHeader(){
	//
	// }
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		return String.format("%10s%10s", trainNo.value,startStation.value);
//		sb.append(trainNo.value).append("\t");
//		sb.append(startStation.value).append("\t");
//		sb.append(arriveStation.value).append("\t");
//		sb.append(startTime).append("\t");
//		sb.append(arriveTime).append("\t");
//		sb.append(lastTime).append("\t");
//		sb.append(hardSeat).append("\t");
//		sb.append(softSeat).append("\t");
//		sb.append(hardSleep).append("\t");
//		sb.append(softSleep).append("\t");
//		return sb.toString();
	}

}
