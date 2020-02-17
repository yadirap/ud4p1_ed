package banco;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestCuentaBancaria {

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void obtenerDigitosControl1() {
		assertEquals("06", CuentaBancaria.obtenerDigitosControl("1234", "5678", "1234567890"));
	}

	@Test
	void obtenerDigitosControl2() {
		assertThrows(Exception.class, () -> {
			CuentaBancaria.obtenerDigitosControl("aaaa", "jaja", "abcdefghij");
		});
	}

	@Test
	void ingresar1() {
		CuentaBancaria cuenta = new CuentaBancaria("Pecador Delapradera", "12345678061234567890", "12345678A");
		try {
			assertEquals(200, cuenta.ingresar(200));
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

	@Test
	void ingresar2() {
		CuentaBancaria cuenta = new CuentaBancaria("Pecador Delapradera", "12345678061234567890", "12345678A");
		Exception exception = assertThrows(Exception.class, () -> {
			cuenta.ingresar(-100);
		});
		assertTrue(exception.getMessage().contains("No se puede ingresar cantidad negativa"));
	}
	
	@Test
	void retirar1() {
		CuentaBancaria cuenta = new CuentaBancaria("Pecador Delapradera", "12345678061234567890", "12345678A");
		try {
			cuenta.setSaldo(500);
			assertEquals(0, cuenta.retirar(500));
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
	
	@Test
	void retirar2() {
		CuentaBancaria cuenta = new CuentaBancaria("Pecador Delapradera", "12345678061234567890", "12345678A");
		try {
			cuenta.setSaldo(100);
			Exception exception = assertThrows(Exception.class, () -> {
				cuenta.retirar(500);
			});
			assertTrue(exception.getMessage().contains("No hay suficiente saldo"));
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
	
	@Test
	void comprobarCCC1() {
		try {
			assertEquals(true, CuentaBancaria.comprobarCCC("12345678061234567890"));
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
	
	@Test
	void comprobarCCC2() {
		try {
			assertEquals(false, CuentaBancaria.comprobarCCC("12345678071234567890"));
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

}
