document.getElementById("vehicle-form").addEventListener("submit", function(event) {
    event.preventDefault();

    const vehicleData = {
        model: document.getElementById("model").value,
        registrationNumber: document.getElementById("registrationNumber").value,
        purchaseDate: document.getElementById("purchaseDate").value,
        customer: { id: document.getElementById("customerId").value }
    };

    fetch("http://localhost:8080/api/vehicles", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(vehicleData)
    })
    .then(response => response.json())
    .then(data => console.log("Vehicle added:", data))
    .catch(error => console.error("Error:", error));
});

document.getElementById("service-record-form").addEventListener("submit", function(event) {
    event.preventDefault();

    const serviceData = {
        serviceDate: document.getElementById("serviceDate").value,
        status: document.getElementById("status").value,
        vehicle: { id: document.getElementById("vehicleId").value },
        serviceAdvisor: { id: document.getElementById("advisorId").value }
    };

    fetch("http://localhost:8080/api/records", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(serviceData)
    })
    .then(response => response.json())
    .then(data => console.log("Service record added:", data))
    .catch(error => console.error("Error:", error));
});

document.getElementById("bom-form").addEventListener("submit", function(event) {
    event.preventDefault();

    const bomData = {
        itemName: document.getElementById("itemName").value,
        quantity: document.getElementById("quantity").value,
        unitCost: document.getElementById("unitCost").value,
        serviceRecord: { id: document.getElementById("serviceRecordId").value }
    };

    fetch("http://localhost:8080/api/bom", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(bomData)
    })
    .then(response => response.json())
    .then(data => console.log("Bill of material added:", data))
    .catch(error => console.error("Error:", error));
});

document.getElementById("customer-form").addEventListener("submit", function(event) {
    event.preventDefault();

    const customerData = {
        name: document.getElementById("customerName").value,
        phone: document.getElementById("phone").value,
        email: document.getElementById("email").value,
        address: document.getElementById("address").value
    };

    fetch("http://localhost:8080/api/customers", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(customerData)
    })
    .then(response => response.json())
    .then(data => console.log("Customer added:", data))
    .catch(error => console.error("Error:", error));
});

document.getElementById("service-representative-form").addEventListener("submit", function(event) {
    event.preventDefault();

    const representativeData = {
        name: document.getElementById("repName").value,
        phone: document.getElementById("repPhone").value,
        expertise: document.getElementById("expertise").value
    };

    fetch("http://localhost:8080/api/representatives", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(representativeData)
    })
    .then(response => response.json())
    .then(data => console.log("Service representative added:", data))
    .catch(error => console.error("Error:", error));
});
