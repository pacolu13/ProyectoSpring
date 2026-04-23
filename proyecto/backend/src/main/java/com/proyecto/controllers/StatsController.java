package com.proyecto.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.DTOs.StatsDTO;
import com.proyecto.config.ApiRoutes;
import com.proyecto.services.StatsService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping(ApiRoutes.STATS)
@RequiredArgsConstructor
public class StatsController {

    private final StatsService statsService;

    @GetMapping
    public StatsDTO getStatsValue() {
        return statsService.getStatsValue();
    }

}
