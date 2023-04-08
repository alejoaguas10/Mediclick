package com.mediclick.controller;

import com.mediclick.entities.Citas;
import com.mediclick.services.CitasService;
import com.mediclick.util.pager.PageRender;
import com.mediclick.util.report.CitasExcelExporter;
import com.lowagie.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
public class CitasController {

    @Autowired
    CitasService citasService;

    // Listing orders
    @GetMapping({"/citas"})
    public String Citas(@RequestParam(name = "page", defaultValue = "0") int page, Model model) {
        Pageable pageRequest = PageRequest.of(page, 10);
        Page<Citas> citas = citasService.findAll(pageRequest);
        PageRender<Citas> pageRender = new PageRender<>("/orders", citas);

        model.addAttribute("title", "Listado de Citas");
        model.addAttribute("citas", citas);
        model.addAttribute("page", pageRender);
        return "citas";
    }

    @GetMapping("/citasform")
    public String MostrarCitasForm(Map<String, Object> model) {
        Citas citas = new Citas();
        model.put("citas", citas);
        model.put("title", "Agregar Nueva Cita");
        return "citasform";
    }

    // Save an order
    @PostMapping("/citasform")
    public String GuardarCitas(@Valid Citas citas, BindingResult result, Model model, SessionStatus status) {
        if (result.hasErrors()) {
            model.addAttribute("title", "Agregar Cita");
            return "citasform";
        }

        citasService.save(citas);
        status.setComplete();
        return "redirect:/citas";
    }

    // Edit an order
    @GetMapping("/citasform/{id}")
    public String EditarCitas(@PathVariable(value = "id") Integer idcitas, Map<String, Object> model) {
        Citas citas = null;
        if (idcitas > 0) {
            citas = citasService.findOne(idcitas);
            if (citas == null) {
                return "redirect:/citas";
            }
        } else {
            return "redirect:/citas";
        }

        model.put("citas", citas);
        model.put("title", "Editar Cita");
        return "citasform";
    }

    // Delete order
    @GetMapping("/eliminarcitas/{id}")
    public String DeleteOrder(@PathVariable(value = "id") Integer idcitas) {
        if (idcitas > 0) {
            citasService.delete(idcitas);
        }
        return "redirect:/citas";
    }

    // Exportacion de excel
    @GetMapping("/citas_excelexportar")
    public void OrderExcelExport(HttpServletResponse response) throws DocumentException, IOException {
        response.setContentType("application/octet-stream");

        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDate = dateFormatter.format(new Date());

        String header = "Content-Disposition";
        String value = "attachment; filename=ListadoCitas_" + currentDate + ".xlsx";

        response.setHeader(header, value);

        List<Citas> citas = citasService.findAll();

        CitasExcelExporter exporter = new CitasExcelExporter(citas);
        exporter.export(response);
    }

}
