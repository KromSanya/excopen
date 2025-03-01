/** @type {import('tailwindcss').Config} */
export default {
    content: [
    "./index.html",
    "./src/**/*.{js,ts,jsx,tsx}",
  ],
  theme: {
  	extend: {
		fontFamily: {
			sans: ['Tinkoff Sans', 'sans-serif']
		},
		zIndex: {
			60: '60',
		},
		backgroundImage: {
			'red-gr': 'linear-gradient(135deg, #FF837E 0%, #FFA9A6 100%)',
			'green-gr': 'linear-gradient(135deg, #76B279 0%, #A7CDA9 100%)',
			'grayscale-gr': 'linear-gradient(0deg, rgba(0, 0, 0, 0.75) 0%, rgba(255, 255, 255, 0) 75%)'
		},
  		colors: {
			primary: {
				0: "#FFDD2D",
				100: "#FFCD32",
			},
			grayscale: {
				0: "#FFFFFF",
				100: "#F6F7F8",
				200: "#F0F1F4",
				250: "#EBEFF3",
				300: "#EAECEE",
				350: "#949495",
				400: "#707071",
				500: "#313132",
				600: "#000000"
			},
			secondary: {
				0: "#9299A2",
				green: "#01A227",
				red: "#F1514A"
			},
			special: {
				0: "#F6F7F8"
			}
  		},
		screens: {
			wide: "1440px",
			huge: "1600px"
		}
  	}
  },
  plugins: [require("tailwindcss-animate")],
}