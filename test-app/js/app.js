// ============================================
// SKYROUTE AIRLINES
// DYNAMIC AIRLINE RESERVATION APPLICATION
// ============================================



// ============================================
// FLIGHT DATA
// ============================================

const flights = [

    {
        number: "SR101",
        from: "Bengaluru",
        to: "Delhi",
        time: "06:30 AM",
        price: 5499
    },

    {
        number: "SR102",
        from: "Bengaluru",
        to: "Delhi",
        time: "10:45 AM",
        price: 6199
    },

    {
        number: "SR103",
        from: "Bengaluru",
        to: "Mumbai",
        time: "08:30 AM",
        price: 4299
    },

    {
        number: "SR104",
        from: "Bengaluru",
        to: "Mumbai",
        time: "06:15 PM",
        price: 4799
    },

    {
        number: "SR105",
        from: "Delhi",
        to: "Mumbai",
        time: "02:00 PM",
        price: 3899
    },

    {
        number: "SR106",
        from: "Hyderabad",
        to: "Chennai",
        time: "05:45 PM",
        price: 3199
    }

];



// ============================================
// GLOBAL VARIABLES
// ============================================

let selectedFlight = null;

let selectedSeat = null;



// ============================================
// SEARCH FLIGHTS
// ============================================

document
    .getElementById("searchButton")
    .addEventListener(
        "click",
        searchFlights
    );



function searchFlights() {

    const from =
        document
            .getElementById("from")
            .value;


    const to =
        document
            .getElementById("to")
            .value;


    const date =
        document
            .getElementById("departureDate")
            .value;


    const error =
        document
            .getElementById("searchError");



    // -------------------------------
    // VALIDATION
    // -------------------------------

    if (!from || !to || !date) {

        error.textContent =
            "Please select From, To and Departure Date.";

        return;

    }


    if (from === to) {

        error.textContent =
            "From and To cities cannot be the same.";

        return;

    }


    error.textContent = "";



    // -------------------------------
    // FIND FLIGHTS
    // -------------------------------

    const matchingFlights =
        flights.filter(

            function (flight) {

                return (

                    flight.from === from &&

                    flight.to === to

                );

            }

        );



    displayFlights(
        matchingFlights
    );

}



// ============================================
// DISPLAY FLIGHTS
// ============================================

function displayFlights(
    flightList
) {

    const section =
        document
            .getElementById(
                "flightResultsSection"
            );


    const container =
        document
            .getElementById(
                "flightResults"
            );



    section.classList.remove(
        "hidden"
    );



    // -------------------------------
    // NO FLIGHTS
    // -------------------------------

    if (
        flightList.length === 0
    ) {

        container.innerHTML = `

            <p>
                No flights available
                for this route.
            </p>

        `;

        return;

    }



    // Clear previous results

    container.innerHTML = "";



    // -------------------------------
    // CREATE DYNAMIC FLIGHT CARDS
    // -------------------------------

    flightList.forEach(

        function (flight) {

            const card =
                document.createElement(
                    "div"
                );


            card.className =
                "flight-card";



            card.innerHTML = `

                <div>

                    <strong>
                        ${flight.number}
                    </strong>

                    <br><br>

                    ${flight.from}

                    →

                    ${flight.to}

                    <br>

                    Departure:
                    ${flight.time}

                </div>


                <div>

                    <strong>
                        ₹${flight.price}
                    </strong>

                    <br>

                    <button

                        class="select-flight"

                        data-flight-number=
                        "${flight.number}">

                        Select

                    </button>

                </div>

            `;


            container.appendChild(
                card
            );

        }

    );



    // -------------------------------
    // DYNAMIC SELECT BUTTONS
    // -------------------------------

    document
        .querySelectorAll(
            ".select-flight"
        )
        .forEach(

            function (button) {

                button.addEventListener(

                    "click",

                    function () {

                        selectFlight(

                            button
                                .dataset
                                .flightNumber

                        );

                    }

                );

            }

        );

}



// ============================================
// SELECT FLIGHT
// ============================================

function selectFlight(
    flightNumber
) {

    selectedFlight =
        flights.find(

            function (flight) {

                return (

                    flight.number ===
                    flightNumber

                );

            }

        );



    const passengerSection =
        document
            .getElementById(
                "passengerSection"
            );


    passengerSection
        .classList
        .remove("hidden");



    passengerSection.scrollIntoView({

        behavior: "smooth"

    });

}



// ============================================
// PASSENGER VALIDATION
// ============================================

document
    .getElementById(
        "continueButton"
    )
    .addEventListener(

        "click",

        validatePassenger

    );



function validatePassenger() {

    const firstName =
        document
            .getElementById(
                "firstName"
            )
            .value
            .trim();


    const lastName =
        document
            .getElementById(
                "lastName"
            )
            .value
            .trim();


    const email =
        document
            .getElementById(
                "email"
            )
            .value
            .trim();


    const phone =
        document
            .getElementById(
                "phone"
            )
            .value
            .trim();


    const error =
        document
            .getElementById(
                "passengerError"
            );



    // -------------------------------
    // REQUIRED FIELD VALIDATION
    // -------------------------------

    if (

        !firstName ||

        !lastName ||

        !email ||

        !phone

    ) {

        error.textContent =
            "All passenger fields are mandatory.";

        return;

    }



    // -------------------------------
    // EMAIL VALIDATION
    // -------------------------------

    const emailPattern =
        /^[^\s@]+@[^\s@]+\.[^\s@]+$/;


    if (
        !emailPattern.test(email)
    ) {

        error.textContent =
            "Please enter a valid email address.";

        return;

    }



    // -------------------------------
    // PHONE VALIDATION
    // -------------------------------

    if (
        !/^\d{10}$/.test(phone)
    ) {

        error.textContent =
            "Phone number must contain 10 digits.";

        return;

    }



    error.textContent = "";



    // Create dynamic seats

    createSeatMap();



    document
        .getElementById(
            "seatSection"
        )
        .classList
        .remove("hidden");



    document
        .getElementById(
            "seatSection"
        )
        .scrollIntoView({

            behavior: "smooth"

        });

}



// ============================================
// CREATE DYNAMIC SEAT MAP
// ============================================

function createSeatMap() {

    const seatMap =
        document
            .getElementById(
                "seatMap"
            );


    seatMap.innerHTML = "";


    selectedSeat = null;



    // Create 24 seats

    for (
        let i = 1;
        i <= 24;
        i++
    ) {

        const seat =
            document.createElement(
                "button"
            );


        seat.className =
            "seat";


        seat.textContent =
            i;


        seat.setAttribute(
            "data-seat-number",
            i
        );



        // -------------------------------
        // BOOKED SEATS
        // -------------------------------

        if (

            i === 3 ||

            i === 7 ||

            i === 14 ||

            i === 21

        ) {

            seat.classList.add(
                "booked"
            );


            seat.disabled = true;

        }



        // -------------------------------
        // AVAILABLE SEATS
        // -------------------------------

        if (!seat.disabled) {

            seat.addEventListener(

                "click",

                function () {

                    selectSeat(
                        seat
                    );

                }

            );

        }



        seatMap.appendChild(
            seat
        );

    }

}



// ============================================
// SELECT SEAT
// ============================================

function selectSeat(
    seat
) {


    // Remove previous selection

    document
        .querySelectorAll(
            ".seat.selected"
        )
        .forEach(

            function (selected) {

                selected.classList.remove(
                    "selected"
                );

            }

        );



    // Select new seat

    seat.classList.add(
        "selected"
    );


    selectedSeat =
        seat.dataset.seatNumber;



    document
        .getElementById(
            "seatError"
        )
        .textContent = "";

}



// ============================================
// CONFIRM BOOKING
// ============================================

document
    .getElementById(
        "confirmButton"
    )
    .addEventListener(

        "click",

        confirmBooking

    );



function confirmBooking() {

    const error =
        document
            .getElementById(
                "seatError"
            );



    // -------------------------------
    // SEAT VALIDATION
    // -------------------------------

    if (!selectedSeat) {

        error.textContent =
            "Please select a seat.";

        return;

    }



    // -------------------------------
    // GET PASSENGER NAME
    // -------------------------------

    const firstName =
        document
            .getElementById(
                "firstName"
            )
            .value;


    const lastName =
        document
            .getElementById(
                "lastName"
            )
            .value;



    // -------------------------------
    // GENERATE DYNAMIC PNR
    // -------------------------------

    const pnr =

        "SR" +

        Math.floor(

            100000 +

            Math.random() *
            900000

        );



    // -------------------------------
    // DISPLAY BOOKING DETAILS
    // -------------------------------

    document
        .getElementById(
            "pnr"
        )
        .textContent =
        pnr;



    document
        .getElementById(
            "confirmationFlight"
        )
        .textContent =
        selectedFlight.number;



    document
        .getElementById(
            "confirmationPassenger"
        )
        .textContent =

        firstName +
        " " +
        lastName;



    document
        .getElementById(
            "confirmationSeat"
        )
        .textContent =
        selectedSeat;



    document
        .getElementById(
            "confirmationPrice"
        )
        .textContent =
        selectedFlight.price;



    // -------------------------------
    // SHOW CONFIRMATION
    // -------------------------------

    document
        .getElementById(
            "confirmationSection"
        )
        .classList
        .remove("hidden");



    document
        .getElementById(
            "confirmationSection"
        )
        .scrollIntoView({

            behavior: "smooth"

        });

}