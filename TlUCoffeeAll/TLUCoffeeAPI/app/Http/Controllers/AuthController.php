<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Models\Customer;
use Illuminate\Support\Facades\Hash;
use Illuminate\Support\Facades\Validator;
use Illuminate\Support\Str;

class AuthController extends Controller
{
    public function register(Request $request)
    {
        $validator = Validator::make($request->all(), [
            'username' => 'required|string|unique:customers,username',
            'password' => 'required|string|min:6',
            'fullname' => 'required|string',
            'address' => 'nullable|string',
            'phone' => 'nullable|string',
            'email' => 'nullable|email|unique:customers,email'
        ]);

        if ($validator->fails()) {
            return response()->json($validator->errors(), 422);
        }

        $customer = new Customer();
        $customer->username = $request->username;
        $customer->password = Hash::make($request->password);
        $customer->fullname = $request->fullname;
        $customer->address = $request->address;
        $customer->phone = $request->phone;
        $customer->email = $request->email;
        $customer->save();

        return response()->json(['message' => 'Đăng ký thành công'], 201);
    }

    public function login(Request $request)
    {
        $customer = Customer::where('username', $request->username)->first();

        if (!$customer || !Hash::check($request->password, $customer->password)) {
            return response()->json(['message' => 'Thông tin đăng nhập không chính xác'], 401);
        }
        $token = Str::random(60);

        return response()->json(['token' => $token], 200);
    }
}
