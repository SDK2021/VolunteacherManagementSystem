var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};
import { Component } from '@angular/core';
import { Router } from '@angular/router';
import Chart from 'chart.js';
import { authentication } from 'src/app/home/shared-services/authentication.service';
import { chartExample1, chartExample2, chartExample3, chartOptions, parseOptions } from 'src/app/variables/charts';
var KidsReportComponent = /** @class */ (function () {
    function KidsReportComponent(_auth, router) {
        this._auth = _auth;
        this.router = router;
        this.clicked = true;
        this.clicked1 = false;
    }
    KidsReportComponent.prototype.ngOnInit = function () {
        this.datasets = [
            [0, 20, 10, 30, 15, 40, 20, 60, 60],
            [0, 20, 5, 25, 10, 30, 15, 40, 40]
        ];
        this.data = this.datasets[0];
        var chartOrders = document.getElementById('chart-orders');
        parseOptions(Chart, chartOptions());
        var ordersChart = new Chart(chartOrders, {
            type: 'bar',
            options: chartExample2.options,
            data: chartExample2.data
        });
        var chartPie = document.getElementById('chart-pie');
        parseOptions(Chart, chartOptions());
        var personalityChart = new Chart(chartPie, {
            type: 'pie',
            options: chartExample3.options,
            data: chartExample3.data
        });
        var chartSales = document.getElementById('chart-sales');
        this.salesChart = new Chart(chartSales, {
            type: 'line',
            options: chartExample1.options,
            data: chartExample1.data
        });
    };
    KidsReportComponent.prototype.updateOptions = function () {
        this.salesChart.data.datasets[0].data = this.data;
        this.salesChart.update();
    };
    KidsReportComponent = __decorate([
        Component({
            selector: 'app-kids-report',
            templateUrl: './kids-report.component.html',
            styleUrls: ['./kids-report.component.css']
        }),
        __metadata("design:paramtypes", [authentication, Router])
    ], KidsReportComponent);
    return KidsReportComponent;
}());
export { KidsReportComponent };
//# sourceMappingURL=kids-report.component.js.map