package org.lapaloma.examen.aaee.vo;

/**
 *
 * Casa: Clase de persistencia que representa un Miembro de un Ministerio.
 * 
 * @author Isidoro Nevares Martín - IES Virgen de la Paloma
 * @date 03 marzo 2026
 * 
 * 
 */
public class Miembro {
	private int identificador;
	private String nif;
	private String nombre;
	private String apellido1;
	private String alias;
	private Ministerio ministerio;

	public Miembro() {
		super();
	}

	public Miembro(int identificador, String nif, String nombre, String apellido1, String alias,
			Ministerio ministerio) {
		super();
		this.identificador = identificador;
		this.nif = nif;
		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.alias = alias;
		this.ministerio = ministerio;
	}

	public int getIdentificador() {
		return identificador;
	}

	public void setIdentificador(int identificador) {
		this.identificador = identificador;
	}

	public String getNif() {
		return nif;
	}

	public void setNif(String nif) {
		this.nif = nif;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido1() {
		return apellido1;
	}

	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public Ministerio getMinisterio() {
		return ministerio;
	}

	public void setMinisterio(Ministerio ministerio) {
		this.ministerio = ministerio;
	}

	@Override
	public String toString() {
		return "Miembro [identificador=" + identificador + ", nif=" + nif + ", nombre=" + nombre + ", apellido1="
				+ apellido1 + ", alias=" + alias + ", ministerio=" + ministerio + "]";
	}
}
