package com.mame.impression.data;

public class ResultDetailDataItemBuilder {
	
	private ResultDetailDataItem mContent = new ResultDetailDataItem();
	
	public ResultDetailDataItemBuilder setMale(int male) {
		mContent.setMale(male);
		return this;
	}

	public ResultDetailDataItemBuilder setFemale(int female) {
		mContent.setFemale(female);
		return this;
	}

	public ResultDetailDataItemBuilder setGenderUnknown(int unknown) {
		mContent.setGenderUnknown(unknown);
		return this;
	}

	public ResultDetailDataItemBuilder setUnder10(int under10) {
		mContent.setUnder10(under10);
		return this;
	}

	public ResultDetailDataItemBuilder setFrom10_20(int from10_20) {
		mContent.setFrom10_20(from10_20);
		return this;
	}

	public ResultDetailDataItemBuilder setFrom20_30(int from20_30) {
		mContent.setFrom20_30(from20_30);
		return this;
	}

	public ResultDetailDataItemBuilder setFrom30_40(int from30_40) {
		mContent.setFrom30_40(from30_40);
		return this;
	}

	public ResultDetailDataItemBuilder setFrom40_50(int from40_50) {
		mContent.setFrom40_50(from40_50);
		return this;
	}

	public ResultDetailDataItemBuilder setFrom50_60(int from50_60) {
		mContent.setFrom50_60(from50_60);
		return this;
	}

	public ResultDetailDataItemBuilder setFrom60_70(int from60_70) {
		mContent.setFrom60_70(from60_70);
		return this;
	}

	public ResultDetailDataItemBuilder setOver70(int over70) {
		mContent.setOver70(over70);
		return this;
	}
	
	public ResultDetailDataItem getResult(){
		return mContent;
	}
	

}
