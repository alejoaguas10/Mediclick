package com.mediclick.controller;

import com.mediclick.entities.Historiales;
import com.mediclick.services.HistorialesService;
import com.mediclick.util.pager.PageRender;
import com.mediclick.util.report.HistorialesExcelExporter;
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
public class HistorialesController {

    @Autowired
    HistorialesService historialesService;

    // Listing orders
    @GetMapping({"/historiales"})
    public String Historiales(@RequestParam(name = "page", defaultValue = "0") int page, Model model) {
        Pageable pageRequest = PageRequest.of(page, 10);
        Page<Historiales> historiales = historialesService.findAll(pageRequest);
        PageRender<Historiales> pageRender = new PageRender<>("/historiales", historiales);

        model.addAttribute("title", "Listado de Historiales Médicos");
        model.addAttribute("historiales", historiales);
        model.addAttribute("page", pageRender);
        return "historiales";
    }

    @GetMapping("/historialesform")
    public String MostrarHistorialesForm(Map<String, Object> model) {
        Historiales historiales = new Historiales();
        model.put("historiales", historiales);
        model.put("title", "Agregar Nuevo Historial");
        return "historialesform";
    }

    // Save an order
    @PostMapping("/historialesform")
    public String GuardarHistoriales(@Valid Historiales historiales, BindingResult result, Model model, SessionStatus status) {
        if (result.hasErrors()) {
            model.addAttribute("title", "Agregar Historial");
            return "historialesform";
        }

        historialesService.save(historiales);
        status.setComplete();
        return "redirect:/historiales";
    }

    // Edit an order
    @GetMapping("/historialesform/{id}")
    public String EditOrder(@PathVariable(value = "id") Integer idhistoriales, Map<String, Object> model) {
        Historiales historiales = null;
        if (idhistoriales > 0) {
            historiales = historialesService.findOne(idhistoriales);
            if (historiales == null) {
                return "redirect:/historiales";
            }
        } else {
            return "redirect:/historiales";
        }

        model.put("historiales", historiales);
        model.put("title", "Editar Historial");
        return "historialesform";
    }

    // Delete order
    @GetMapping("/eliminarhistoriales/{id}")
    public String EliminarHistoriales(@PathVariable(value = "id") Integer idcitas) {
        if (idcitas > 0) {
            historialesService.delete(idcitas);
        }
        return "redirect:/historiales";
    }

    // Exportaciond de excel
    @GetMapping("/historiales_excelexportar")
    public void HistorialesExcelExportar(HttpServletResponse response) throws DocumentException, IOException {
        response.setContentType("application/octet-stream");

        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDate = dateFormatter.format(new Date());

        String header = "Content-Disposition";
        String value = "attachment; filename=ListadoHistoriales_" + currentDate + ".xlsx";

        response.setHeader(header, value);

        List<Historiales> historiales = historialesService.findAll();

        HistorialesExcelExporter exporter = new HistorialesExcelExporter(historiales);
        exporter.export(response);
    }

}
