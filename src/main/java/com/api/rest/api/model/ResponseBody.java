package com.api.rest.api.model;

import java.util.ArrayList;
import java.util.List;

public class ResponseBody {

	public String BrandName;
	public String Id;
	public String LaptopName;
	public Features features;
	
}

class Features{
	public List<String> feature = new ArrayList();
}