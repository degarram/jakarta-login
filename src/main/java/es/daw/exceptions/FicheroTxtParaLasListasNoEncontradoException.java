package es.daw.exceptions;

import java.io.FileNotFoundException;

public class FicheroTxtParaLasListasNoEncontradoException extends FileNotFoundException {
  public FicheroTxtParaLasListasNoEncontradoException() {
    super("Fichero de la lista no encontrado");
  }
}
