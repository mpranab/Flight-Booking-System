import { Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { ContactUsComponent } from './contact-us/contact-us.component';
import { AboutUsComponent } from './about-us/about-us.component';
import { RegisterComponent } from './register/register.component';
import { LoginComponent } from './login/login.component';
import { UserpageComponent } from './userpage/userpage.component';
import { FlightComponent } from './flight/flight.component';
import { UserComponent } from './user/user.component';
import { AddBookingComponent } from './add-booking/add-booking.component';
import { AdminComponent } from './admin/admin.component';
import { AddFlightComponent } from './add-flight/add-flight.component';
import { AllFlightsComponent } from './all-flights/all-flights.component';
import { UpdateFlightComponent } from './update-flight/update-flight.component';
import { DeleteFlightComponent } from './delete-flight/delete-flight.component';
import { PaymentComponent } from './payment/payment.component';
import { AllBookingsComponent } from './all-bookings/all-bookings.component';

export const routes: Routes = [
    {path: '', redirectTo: 'home', pathMatch: 'full' },
    {path:"home",component:HomeComponent},
    {path:"contactUs",component:ContactUsComponent},
    {path:"aboutUs",component:AboutUsComponent},
    {path:"signUp",component:RegisterComponent},
    {path:"login",component:LoginComponent},
    {path:"userpage",component:UserpageComponent},
    {path:"flight",component:FlightComponent},
    {path:"user",component:UserComponent},
    {path:"addbooking",component:AddBookingComponent},
    {path:"admin",component:AdminComponent},
    {path:"addflight",component:AddFlightComponent},
    {path:"allflights",component:AllFlightsComponent},
    {path:"updateflight/:fligthNo",component:UpdateFlightComponent},
    {path:"deleteflight",component:DeleteFlightComponent},
    {path:"payment",component:PaymentComponent},
    {path:"allbookings",component:AllBookingsComponent}
];
