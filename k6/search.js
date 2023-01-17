import http from 'k6/http';

export const options = {
//    tlsAuth: [
//        {
//            cert: open('./cert.pem'),
//            key: open('./key.pem'),
//        },
//    ],
    thresholds: {
        http_req_failed: ['rate<0.01'], // http errors should be less than 1%
        http_req_duration: ['p(95)<100'], // 95% of requests should be below 100ms
    },

    stages: [
        { duration: '5s', target: 2 },
        { duration: '15s', target: 10 },
    ],

//    scenarios: {
//        open_model: {
//            executor: 'constant-arrival-rate',
//            rate: 5,
//            timeUnit: '1s',
//            duration: '20s',
//            preAllocatedVUs: 50,
//        },
//    },
}

//const body = open("./sample.json")

const params = {
    headers: {
        'Content-Type' : 'application/json'
    }
}

export default function () {
    const url = "http://localhost:8061/api/coordinates?lat=50.2338&lng=19.021&size=800&zoom=6"
    http.get(url,params);
}