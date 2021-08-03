import { group } from '@angular/animations';
import { NumberSymbol } from '@angular/common';
import { Component, Injectable, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { KidService } from 'src/app/admin/shared-services/kid.service';
import { Kid } from 'src/app/core/model/kid';
import { authentication } from 'src/app/home/shared-services/authentication.service';
import { KidsService } from 'src/app/kids/shared-services/kids.service';

@Component({
  selector: 'app-admin-kids-list',
  templateUrl: './admin-kids-list.component.html',
  styleUrls: ['./admin-kids-list.component.css']
})

@Injectable({
  providedIn: 'root'
})

export class AdminKidsListComponent implements OnInit {


  groups: Array<string>
  villages: Array<string>
  userType: string
  selectedVillage: number
  selectedArea: number
  selectedGroup: number
  totalKidsPages: number

  showSpinner: boolean = false
  noVillages: boolean = false
  kLength: number;


  standards: number[] = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12]

  showLevels: boolean = false
  showStandards: boolean = false

  page: number = 0
  filter: string
  @Input() label: string


  search: string = ''

  kidslist: Array<Kid> = new Array()

  filterSelected:number=null

  stdSelected:number=1
  levelSelected:number=1

  constructor(private kidService: KidService, private kidsService: KidsService, private _auth: authentication, private router: Router) {
  }


  ngOnInit() {
    this.page = 0
    this.getkids(this.page, "all")
  }

  handleError(error) {
    console.log(error);
    console.log(error.status);

    if (error.status === 500) {
      this.router.navigate(['internal-server-error'])
    }
    else {
      this.router.navigate(['error-page'])
    }
  }

 
  onScroll() {
    if (this.page < this.totalKidsPages - 1) {
      this.page += 1
      if(this.filterSelected==1)
        this.getPageableKidsByLevel(this.page,this.levelSelected)
      else if(this.filterSelected==2)
        this.getPageableKidsByStandard(this.page, this.stdSelected)
      else
        this.getPageableKids(this.page);
    }
  }
  getPageableKids(page: number) {
    if (this.filter === "all") {
      this.kidsService.getkidslist(page).subscribe(data => {
        data['content'].forEach(kid => {
          this.kidslist.push(kid)
        });
      })
    }
    if (this.filter === "v") {
      this.kidsService.getAllKidsByVillage(this.page, this.selectedVillage).subscribe(data => {
        data['content'].forEach(kid => {
          this.kidslist.push(kid)
        });
      })
    }
    if (this.filter === "g") {
      this.kidsService.getAllKidsByGroup(this.page, this.selectedGroup).subscribe(data => {
        data['content'].forEach(kid => {
          this.kidslist.push(kid)
        });
      })
    }
    if (this.filter === "a") {
      this.kidsService.getAllKidsByArea(this.page, this.selectedArea).subscribe(data => {
        data['content'].forEach(kid => {
          this.kidslist.push(kid)
        });
      })
    }
    if (this.filter === "vg") {
      this.kidsService.getAllKidsByVillageAndGroup(this.page, this.selectedVillage, this.selectedGroup).subscribe(data => {
        data['content'].forEach(kid => {
          this.kidslist.push(kid)
        });
      })
    }
    if (this.filter === "vga") {
      this.kidsService.getAllKidsByAreaAndGroupAndVillage(this.page, this.selectedArea, this.selectedGroup, this.selectedVillage).subscribe(data => {
        data['content'].forEach(kid => {
          this.kidslist.push(kid)
        });
      })
    }
  }

  getPageableKidsByLevel(page: number,level:number) {   
    this.showSpinner = true
    this.kidService.getKidsByLevel(page, level).subscribe(data => {
        data['content'].forEach(kid => {
          this.kidslist.push(kid)
          this.showSpinner=false
        });
      }, error => {
        this.handleError(error)
      })
  }

  getPageableKidsByStandard(page: number,standard:number) {   
    this.showSpinner = true
    this.kidService.getKidsByStandard(page, standard).subscribe(data => {
        data['content'].forEach(kid => {
          this.kidslist.push(kid)
          this.showSpinner=false
        });
      }, error => {
        this.handleError(error)
      })
  }

  

  getkids(page: number, filter: string) {
    this.page = 0
    this.filter = filter
    this.showSpinner = true
    this.kidsService.getkidslist(page).subscribe(data => {
      this.kidslist = data['content'];
      this.totalKidsPages = data['totalPages']
      this.kLength = this.kidslist.length
      this.showSpinner = false
      this.kidslist = this.calculateAge(this.kidslist)

    });
  }

  getKidsByTaluka(talukaId: number) {
    this.page = 0
    this.kidsService.getkidslist(0).subscribe(data => {
      for (var kid of this.kidslist) {
        if (kid.village.taluka.talukaId == talukaId) {
          this.kidslist.concat(kid)
          this.kidslist = this.calculateAge(this.kidslist)
        }
      }
    });
  }

  getKidsByVillage(villageId: number, filter: string) {
    this.page = 0
    this.filter = filter
    this.selectedVillage = villageId
    this.kidsService.getAllKidsByVillage(0, villageId).subscribe(data => {
      this.kidslist = data['content']
      this.kidslist = this.calculateAge(this.kidslist)

    })
  }

  getKidsByArea(areaId: number, filter: string) {
    this.page = 0;
    this.filter = filter
    this.selectedArea = areaId
    this.kidsService.getAllKidsByArea(0, areaId).subscribe(data => {
      this.kidslist = data['content']
      this.kidslist = this.calculateAge(this.kidslist)
    })
  }

  getKidsByVillageAndGroup(villageId: number, groupId: number, filter: string) {
    this.page = 0;
    this.filter = filter
    this.selectedVillage = villageId
    this.selectedGroup = groupId
    this.kidsService.getAllKidsByVillageAndGroup(0, villageId, groupId).subscribe(data => {
      this.kidslist = data['content']
      this.kidslist = this.calculateAge(this.kidslist)
    })
  }

  getKidsByAreaAndGroupAndVillage(areaId: number, groupId: number, villageId: number, filter: string) {
    this.page = 0
    this.filter = filter
    this.selectedVillage = villageId
    this.selectedArea = areaId
    this.selectedGroup = groupId
    this.kidsService.getAllKidsByAreaAndGroupAndVillage(0, areaId, groupId, villageId).subscribe(data => {
      this.kidslist = data['content']
      this.kidslist = this.calculateAge(this.kidslist)
    })
  }

  getKidsByGroup(groupId: number, filter: string) {
    this.selectedGroup = groupId
    this.filter = filter
    this.page = 0
    this.kidsService.getAllKidsByGroup(this.page, groupId).subscribe(data => {
      this.kidslist = data['content']
    })
  }

  calculateAge(kidsList: Array<Kid>): Array<Kid> {
    for (let k of kidsList) {
      let currentDate = new Date()
      let bDate = new Date(k.dob)

      let diffInSec = Math.abs(currentDate.getTime() - bDate.getTime())
     
      k.age = (diffInSec / (1000 * 3600 * 24) / 365) + 1
      let array: Array<string> = k.age.toString().split('.')
      k.age = Number.parseInt(array[0])

    }
    return kidsList

  }

  getUserType(): string {
    //console.log(this.router.url);
    let array: Array<string> = new Array()
    array = this.router.url.split('/')
    //console.log(array[1]);
    return array[1]

  }

  getKidsByLevel(page: number, level: number) {
    this.page = 0
    this.showSpinner = true
    this.kidService.getKidsByLevel(page, level).subscribe(data => {
      this.kidslist = data['content'];
      this.totalKidsPages = data['totalPages']
      this.kLength = this.kidslist.length
      this.showSpinner = false
      this.kidslist = this.calculateAge(this.kidslist)

    });
  }

  getKidsByStandard(page: number, standard: number) {
    this.page = 0
    this.showSpinner = true
    this.kidService.getKidsByStandard(page, standard).subscribe(data => {
      this.kidslist = data['content'];
      this.totalKidsPages = data['totalPages']
      this.kLength = this.kidslist.length
      this.showSpinner = false
      this.kidslist = this.calculateAge(this.kidslist)

    });
  }

  selectedLevel(event) {
    this.levelSelected=Number.parseInt(event.target.value)
    this.getKidsByLevel(0, Number.parseInt(event.target.value))
  }

  selectedStandard(event) {
    this.stdSelected=Number.parseInt(event.target.value)
    this.getKidsByStandard(0, Number.parseInt(event.target.value))
  }

  selectedFilter(event) {
    if (event.target.value === '1') {
      this.filterSelected=1
      this.showStandards=false
      this.showLevels = true
      this.getKidsByLevel(0, 1)
    }
    else if(event.target.value === '2') {
      this.filterSelected=2
      this.showLevels=false
      this.showStandards = true
      this.getKidsByStandard(0, 1)
    }
    else
    {
      this.filterSelected=3
      this.showLevels=false
      this.showStandards = false
      this.getkids(0, "all")
    }
  }

}
