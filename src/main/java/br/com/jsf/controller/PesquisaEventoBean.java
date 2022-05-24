package br.com.jsf.controller;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletContext;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.FillPatternType;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Image;

import br.com.jsf.model.Evento;
import br.com.jsf.model.EventoFilter;
import br.com.jsf.service.EventoService;
import br.com.jsf.util.jsf.FacesUtil;
import lombok.Getter;
import lombok.Setter;

/**
 * Classe de controle respnsavel pela comunicacao com a pagina de Pesquisa de
 * Evento. Gerenciada pelo CDI. Escopo de visualizacao.
 */

@Named
@ViewScoped
public class PesquisaEventoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Getter
	private Date currentDate = new Date();

	@Getter
	private LocalTime currentTime = LocalTime.now().withNano(0);

	@Getter
	@Setter
	private Evento eventoSelecionado;

	@Getter
	@Setter
	private EventoFilter filter;

	@Getter
	private List<Evento> eventos;

	@Inject
	private EventoService service;

	public PesquisaEventoBean() {
		this.filter = new EventoFilter();
	}

	/**
	 * Metodo para busca no banco de dados.
	 */
	public void find() {

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
		}

		eventos = service.findEvent(filter);
	}

	/**
	 * Metodo que remove um evento da lista.
	 */
	public void remove() {
		System.out.println("PesquisaEventoBean.remove()");

		try {
			service.removeEvent(eventoSelecionado);
			eventos.remove(eventoSelecionado);

			FacesUtil.addInfoMessage("Evento " + eventoSelecionado.getNome() + " excluído com sucesso!");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void postProcessPDF(Object document) throws IOException, BadElementException, DocumentException {
		Document pdf = (Document) document;
		ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext()
				.getContext();

		String logo = servletContext.getRealPath("") + File.separator + "resources" + File.separator + "images"
				+ File.separator + "eventos.png";
		pdf.add(Image.getInstance(logo));

	}
	
	public void postProcessXLS(Object document) {
		HSSFWorkbook wb = (HSSFWorkbook) document;
		HSSFSheet sheet = wb.getSheetAt(0);
		HSSFRow header = sheet.getRow(0);
		HSSFCellStyle cellStyle = wb.createCellStyle();
		cellStyle.setFillForegroundColor(HSSFColor.HSSFColorPredefined.GREY_25_PERCENT.getIndex());
		cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		
		for (int i = 0; i < header.getPhysicalNumberOfCells(); i++) {
			header.getCell(i).setCellStyle(cellStyle);
		}
	}
	
}