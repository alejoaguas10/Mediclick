package com.mediclick.controller;

import com.mediclick.entities.Insumos;
import com.mediclick.util.report.InsumosExcelExporter;
import com.mediclick.services.InsumosService;
import com.mediclick.util.pager.PageRender;
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
public class InsumosController {

    @Autowired
    InsumosService insumosService;

    // Listing products
    @GetMapping({"/insumos"})
    public String Insumos(@RequestParam(name = "page",defaultValue = "0") int page, Model model){
        Pageable pageRequest = PageRequest.of(page, 10);
        Page<Insumos> insumos = insumosService.findAll(pageRequest);
        PageRender<Insumos> pageRender = new PageRender<>("/insumos", insumos);

        model.addAttribute("title", "Listado de Insumos en Inventario");
        model.addAttribute("insumos", insumos);
        model.addAttribute("page", pageRender);
        return "insumos";
    }

    @GetMapping("/insumosform")
    public String mostrarInsumosForm(Map<String,Object> model) {
        Insumos insumos = new Insumos();
        model.put("insumos", insumos);
        model.put("title", "Agregar Nuevo Insumo");
        return "insumosform";
    }

    // Save a product
    @PostMapping("/insumosform")
    public String guardarInsumos(@Valid Insumos insumos, BindingResult result, Model model, SessionStatus status){
        if (result.hasErrors()){
            model.addAttribute("title", "Agregar Insumo");
            return "insumosform";
        }

        insumosService.save(insumos);
        status.setComplete();
        return "redirect:/insumos";
    }

    // Edit a product
    @GetMapping("/insumosform/{id}")
    public String EditarInsumos(@PathVariable(value = "id") Integer idinsumos, Map<String, Object> model) {
        Insumos insumos = null;
        if(idinsumos > 0) {
            insumos = insumosService.findOne(idinsumos);
            if(insumos == null) {
                return "redirect:/insumos";
            }
        }
        else {
            return "redirect:/insumos";
        }

        model.put("insumos", insumos);
        model.put("title", "Editar Insumo");
        return "insumosform";
    }

    // Delete product
    @GetMapping("/eliminarinsumos/{id}")
    public String EliminarInsumos(@PathVariable(value = "id") Integer idinsumos) {
        if(idinsumos > 0) {
            insumosService.delete(idinsumos);
        }
        return "redirect:/insumos";
    }

    // Exportacion de excel
    @GetMapping("/insumos_excelexportar")
    public void insumosExcelExportar(HttpServletResponse response) throws DocumentException, IOException {
        response.setContentType("application/octet-stream");

        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDate = dateFormatter.format(new Date());

        String header = "Content-Disposition";
        String value = "attachment; filename=ListadoInsumos" + currentDate + ".xlsx";

        response.setHeader(header, value);

        List<Insumos> insumos = insumosService.findAll();

        InsumosExcelExporter exporter = new InsumosExcelExporter(insumos);
        exporter.export(response);
    }
}
