package resources;

public enum APIResourses {
	AddPlaceAPI("/maps/api/place/add/json"),
	getPlaceAPI("/maps/api/place/get/json"),
	DeletePlaceAPI("/maps/api/place/delete/json");
	
	private String resourses;
	APIResourses(String resourses)
	{
		this.resourses=resourses;
	}
	
	public String getResourses()
	{
		return resourses;
	}

}
