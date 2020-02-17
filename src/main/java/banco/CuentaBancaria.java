package banco;



public class CuentaBancaria {
	private static final int MAXNOMBRE = 100;
	private static final int MINNOMBRE = 10;
	/*
	 * 
	 * 
	 */
	private String titular, entidad, oficina, numCuenta, DC, CCC, DNI;
	private double saldo;

	/*
	 * Crear CCC para seguir con el DC
	 */
	public CuentaBancaria(String titular, String entidad, String oficina, String DC, String numCuenta, String DNI) {
		super();

		this.titular = titular;
		this.entidad = entidad;
		this.oficina = oficina;
		this.numCuenta = numCuenta;
		this.DC = DC;
		this.DNI = DNI;
	}

	public CuentaBancaria(String titular, String CCC, String DNI) {
		super();
		comprobar(titular);

		if (comprobarCCC(CCC) == true) {
			setCCC(CCC);
		} else {
			throw new IllegalArgumentException("El CCC introducido no es válido");
		}

		this.CCC = CCC.replace(" ", "");
		this.entidad = CCC.substring(0, 4);
		this.oficina = CCC.substring(4, 8);
		this.DC = CCC.substring(8, 10);
		this.numCuenta = CCC.substring(10, 20);
		this.DNI = DNI;
	}

	public String getTitular() {
		return titular;
	}

	public void setTitular(String titular) {
		this.titular = titular;
	}

	public String getEntidad() {
		return entidad;
	}

	public void setEntidad(String entidad) {
		this.entidad = entidad;
	}

	public String getOficina() {
		return oficina;
	}

	public void setOficina(String oficina) {
		this.oficina = oficina;
	}

	public String getNumCuenta() {
		return numCuenta;
	}

	public void setNumCuenta(String numCuenta) {
		this.numCuenta = numCuenta;
	}

	public String getDC() {
		return DC;
	}

	public void setDC(String dC) {
		DC = dC;
	}

	public String getCCC() {
		return CCC;
	}

	public void setCCC(String cCC) {
		CCC = cCC;
	}

	public String getDNI() {
		return DNI;
	}

	public void setDNI(String dNI) {
		DNI = dNI;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public static String obtenerDigitosControl(String entidad, String oficina, String numCuenta) {

		String oficinaEntidad, numeroCuenta = "", dijitoControl = "";
		int DC = 0;
		int arrayCCC[] = { 1, 2, 4, 8, 5, 10, 9, 7, 3, 6 };
		for (int j = 0; j < 2; j++) {
			if (j == 0) {
				oficinaEntidad = ("00" + entidad) + oficina;
			} else {
				oficinaEntidad = numCuenta;
			}
			for (int i = 0, y = 1; i < oficinaEntidad.length(); i++) {
				numeroCuenta = oficinaEntidad.substring(i, y);
				int numCuenta1 = Integer.parseInt(numeroCuenta);
				numCuenta1 = numCuenta1 * arrayCCC[i];
				y++;
				DC += numCuenta1;
			}
			DC = DC % 11;
			DC = 11 - DC;
			if (DC == 11) {
				DC = 0;
			} else if (DC == 10) {
				DC = 1;
			}
			dijitoControl += String.valueOf(DC);
		}

		return dijitoControl;

	}

	public double ingresar(double cantidad) throws Exception {

		if (cantidad < 0)
			throw new Exception("No se puede ingresar cantidad negativa");

		saldo = getSaldo() + cantidad;

		return saldo;

	}

	public double retirar(double cantidad) throws Exception {
		if (cantidad < 0)
			throw new Exception("No se puede retirar cantidad negativa");
		if (getSaldo() < cantidad)
			throw new Exception("No hay suficiente saldo");

		saldo = getSaldo() - cantidad;

		return saldo;

	}

	public static boolean comprobarCCC(String CCC) {
		String entidad, oficina, dC, nCuenta;
		CCC = CCC.replace(" ", "");
		entidad = CCC.substring(0, 4);
		oficina = CCC.substring(4, 8);
		dC = CCC.substring(8, 10);
		nCuenta = CCC.substring(10, 20);

		if (dC.equals(obtenerDigitosControl(entidad, oficina, nCuenta))) {
			return true;
		} else {
			return false;
		}

	}

	public void comprobar(String titular) {
		if (titular.length() > MINNOMBRE && titular.length() < MAXNOMBRE) {
			setTitular(titular);

		} else {
			throw new IllegalArgumentException("El titular no cumple con los requisitos necesarios");
		}

	}

	@Override
	public String toString() {
		return "CuentaBancaria del usuario " + titular + ": " + CCC + ", saldo: " + saldo + ".";
	}

}
