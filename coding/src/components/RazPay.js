import '../css/RazorPay.css';


import React, { useEffect, useState } from 'react';

function RazPay() {

  const [amount, setAmount] = useState("");
  const [solve, setSolve] = useState(amount)

  const url = "https://jsonplaceholder.typicode.com/comments"


  async function fetchData() {
    const data = fetch(url).then((res) => res.json())
      .then((getData) => console.log("set damount data", setAmount(getData[0].id)))
      
  }

  useEffect(() => {
    fetchData()
  }, [])


  const handleSubmit = (e) => {
    e.preventDefault();
    console.log("amount wala data", amount)

    if (amount === "") {
      alert("please enter a valid amount");
    } else {
      const options = {
        key: "rzp_test_yKt32uKI6iM9vt",
        key_secret: "WdkV3mRiU6pFEy7AtPUNJ58i",
        amount: amount * 100,
        currency: "INR",
        name: "HAARMK_PROJECTS",
        description: "for testing purpose",
        handler: function (response) {
          alert(response.razorpay_payment_id);
        },
        prefill: {
          name: "",
          email: "Dummy0@gmail.com",
          contact: "0000000000"
        },
        notes: {
          address: "Razorpay Corporate office"
        },
        theme: {
          color: "#3399cc"
        }
      };
      
      var pay = new window.Razorpay(options);
      pay.open();
      
    }
  }

  return (
    <div className="App">
      <h2>HAARMK Payment Portal</h2>
      <br />
      <div className='input'>
        <p>You are paying</p>
        <input type="number" value={amount} onChange={(e) => setAmount(e.target.value)} readOnly />
      </div>
      <button className='btn' onClick={handleSubmit}>Click here to pay</button>
    </div>
  );
}

export default RazPay;



