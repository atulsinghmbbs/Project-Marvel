import './RazPay.css';
import React, { useState } from 'react';
import { useEffect } from 'react';
import { useLocation } from 'react-router-dom';
import { bakendBaseUrl } from './BaseUrl';
import { bakendHeader } from './BaseUrl';

function RazPay() {
  const [amount, setAmount] = useState("");

  const [payId, setPayId] = useState("")

  const [redirectToDashboard, setredirectToDashboard] = useState("")

  const [loading, setLoading] = useState(true)

  const location = useLocation()
  console.log('razorpay me id', location.state.razorPayAllData)

  useEffect(() => {
    setPayId(location.state.razorPayAllData.razorpayOrderId)
    setAmount(location.state.razorPayAllData.total)
    window.scroll(0, 0)
  }, [location.state.razorPayAllData])

  console.log("payId", payId)
  console.log(amount)



  const handleSubmit = async (e) => {
    e.preventDefault();

    if (amount === "") {
      alert("please enter a valid amount");
    } else {
      var options = {
        key: "rzp_test_MfVx6zeC4NgkKw",
        currency: "INR",
        order_id: payId,
        handler: function (response) {
          // setPaymentResponse(response)
          sendPaymentToBackend(response)

        },
        image: "https://example.com/your_logo",
        prefill: {
          contact: "",
        },
        notes: {
          address: "Razorpay Corporate office",
        },
        theme: {
          color: "#3399cc",
        },
      };

      // try {
      //   const response = await fetch("/your-server-side-script", {
      //     method: "POST",
      //     headers: {
      //       "Content-Type": "application/json",
      //     },
      //     body: JSON.stringify({
      //       amount: options.amount,
      //       currency: options.currency,
      //     }),
      //   });

      //   const data = await response.json();
      //   options.handler = function (response) {
      //     alert("Payment successful. Transaction ID: " + response.razorpay_payment_id);
      //   };
      // } catch (err) {
      //   options.handler = function (response) {
      //     alert("Payment failed. Transaction ID: " + response.razorpay_payment_id);
      //   };
      // }

      var pay = new window.Razorpay(options);
      pay.open();
    }
  };

  // console.log("payement Response", paymentResponse);

  const sendPaymentToBackend = (response) => {
    console.log("res data", response);
    fetch(`${bakendBaseUrl}/services/activate`, {
      method: 'POST',
      body: JSON.stringify({
        ...response
      }),
      headers: bakendHeader
    })

      .then((response) => response.json())
      .then((json) => (setredirectToDashboard(json)));
    setLoading(false)
  }

  console.log("get payment response from backend", redirectToDashboard)

  // 
  //   if (loading === false) {
  //     window.location.href = "/UserPanel"
  //   }



  return (
    <div className='payment-portal-wrapper' style={{ marginTop: 100 }}>
      <div className="payment-container">
        <h2 className=' text-center payment-heading'>Haarmk Payment Portal</h2>
        <p className='you-paying'>You are paying!</p>
        <input type="number" value={amount} readOnly />
        <button onClick={handleSubmit}>
          Click here to pay
        </button>
      </div>
    </div>
  );
}

export default RazPay;


