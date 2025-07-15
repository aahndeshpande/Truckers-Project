export interface User {
    id: number;
    username: string;
    email: string;
    role: 'USER' | 'OWNER' | 'ADMIN';
    createdAt: string;
    updatedAt: string;
}

export interface AuthResponse {
    token: string;
    user: User;
}

export interface LoginCredentials {
    email: string;
    password: string;
}

export interface RegisterCredentials {
    username: string;
    email: string;
    password: string;
    confirmPassword: string;
}

export interface Event {
    id: number;
    name: string;
    description: string;
    location: string;
    address: string;
    city: string;
    state: string;
    zipCode: string;
    startTime: string;
    endTime: string;
    capacity: number;
    imageUrl: string;
    isPublic: boolean;
    contactPerson: string;
    contactEmail: string;
    contactPhone: string;
    websiteUrl: string;
    socialMediaUrl: string;
    owner: Owner;
    community: Community;
    foodTrucks: FoodTruck[];
}

export interface Community {
    id: number;
    name: string;
    address: string;
    city: string;
    state: string;
    zipCode: string;
    contactPerson: string;
    contactEmail: string;
    contactPhone: string;
    description: string;
    websiteUrl: string;
    socialMediaUrl: string;
    imageUrl: string;
    owner: Owner;
    foodTrucks: FoodTruck[];
    events: Event[];
}

export interface FoodTruck {
    id: number;
    name: string;
    description: string;
    imageUrl: string;
    menuItems: MenuItem[];
    owner: Owner;
    communities: Community[];
    events: Event[];
}

export interface MenuItem {
    id: number;
    name: string;
    description: string;
    price: number;
    imageUrl: string;
    foodTruck: FoodTruck;
}

export interface Owner {
    id: number;
    name: string;
    email: string;
    phone: string;
    businessName: string;
    businessDescription: string;
    businessLicense: string;
    businessAddress: string;
    businessCity: string;
    businessState: string;
    businessZipCode: string;
    businessPhone: string;
    businessEmail: string;
    businessWebsite: string;
    businessSocialMedia: string;
}
