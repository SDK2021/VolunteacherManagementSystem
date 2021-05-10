var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
import { Component } from '@angular/core';
import Chart from 'chart.js';
// core components
import { chartOptions, parseOptions, chartExample1, chartExample2 } from "src/app/variables/charts";
var DashboardComponent = /** @class */ (function () {
    function DashboardComponent() {
        this.clicked = true;
        this.clicked1 = false;
    }
    DashboardComponent.prototype.ngOnInit = function () {
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
        var chartSales = document.getElementById('chart-sales');
        this.salesChart = new Chart(chartSales, {
            type: 'line',
            options: chartExample1.options,
            data: chartExample1.data
        });
    };
    DashboardComponent.prototype.updateOptions = function () {
        this.salesChart.data.datasets[0].data = this.data;
        this.salesChart.update();
    };
    DashboardComponent = __decorate([
        Component({
            selector: 'app-dashboard',
            templateUrl: './dashboard.component.html',
            styleUrls: ['./dashboard.component.scss']
        })
    ], DashboardComponent);
    return DashboardComponent;
}());
export { DashboardComponent };
//# sourceMappingURL=dashboard.component.js.map