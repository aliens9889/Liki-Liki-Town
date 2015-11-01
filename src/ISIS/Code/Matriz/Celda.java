package ISIS.Code.Matriz;

public class Celda {

	private Terreno terrenoRef = null;
	private Construccion constRef = null;

	public Celda() {
		/* Empty */
	}
	
	public Celda(Terreno terrenoRef, Construccion constRef) {
		this.terrenoRef = terrenoRef;
		this.constRef = constRef;
	}
	
	
	
	public Terreno getTerrenoRef() {
		return terrenoRef;
	}

	public void setTerrenoRef(Terreno terrenoRef) {
		this.terrenoRef = terrenoRef;
	}



	public Construccion getConstRef() {
		return constRef;
	}

	public void setConstRef(Construccion constRef) {
		this.constRef = constRef;
	}

	// Parametros
	private int salud;
	private double sigmaSalud;
	private int educacion;
	private double sigmaEducacion;
	private int violencia;
	private double sigmaViolencia;
	private int delincuencia;
	private double sigmaDelicuencia;
	private int empleo;
	private double sigmaEmpleo;
	private int deporte;
	private double sigmaDeporte;
	private int seguridad;
	private double sigmaSeguridad;

}
