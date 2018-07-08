package communication;


public class RequAutre extends ARequette{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String type , logType;
	
	public RequAutre(String logType,String type,int nbChambre, int nbEtage, Boolean cuisinSepar, Boolean jardin,Boolean toit) {
		super(LogType.Null, Type.Null, nbChambre, nbEtage, cuisinSepar, jardin, toit);
		this.logType = logType ;
		this.type = type;
	}

	public String gettype() {
		return type;
	}

	public void settype(String type) {
		this.type = type;
	}

	public String getLogtype() {
		return logType;
	}

	public void setLogType(String logType) {
		this.logType = logType;
	}

	public static RequAutre getARequette(String requet){
		if(requet.isEmpty() || requet==null) return null;
		
		RequAutre aReq;
		String[] criteres;
		
		try{
		    criteres = requet.split(" ");				
		
			aReq = new RequAutre(criteres[1],criteres[2],Integer.parseInt(criteres[3]),Integer.parseInt(criteres[4]),
					Boolean.valueOf(criteres[5]),Boolean.valueOf(criteres[6]),Boolean.valueOf(criteres[7]));
			
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	
		try{
			if(criteres[8].equals("]")) return aReq;
			aReq.setPrix(Integer.parseInt(criteres[8]));
			if(criteres[9].equals("]")) return aReq;
			aReq.setSurface(Integer.parseInt(criteres[9]));
			if(criteres[10].equals("]")) return aReq;
			aReq.setAdress(criteres[10]);
			if(criteres[11].equals("]")) return aReq;
			aReq.setNomProp(criteres[11]);
			if(criteres[12].equals("]")) return aReq;
			aReq.setTel(criteres[12]);
		}catch(Exception e){ e.printStackTrace(); return aReq; }
		
		return aReq;
	}

	
	@Override
	public String toString() {
		return "[ " + logType + " " + type + " " + nbChambre + " " + nbEtage + " "
				+ cuisinSepar + " " + jardin + " " + toit + " " + prix + " " + surface
				+ " " + adresse + " " + nomProp + " " + tel + " ]";
	}

	@Override
	public boolean equals(Object obj) {
		RequAutre other = (RequAutre) obj ;
		return toString().equals(other.toString());
	}
	
}
