import React, { useState } from 'react';
import { X, Lock, CheckCircle } from 'lucide-react';

interface LoginModalProps {
  onClose: () => void;
  onLoginSuccess: (email: string) => void;
  isLoggedIn: boolean;
  onLogout: () => void;
}

export const LoginModal: React.FC<LoginModalProps> = ({
  onClose,
  onLoginSuccess,
  isLoggedIn,
  onLogout,
}) => {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    if (!email.trim()) return;
    onLoginSuccess(email.trim());
    onClose();
  };

  return (
    <div className="fixed inset-0 z-50 bg-black/60 backdrop-blur-xs flex items-center justify-center p-4">
      <div className="bg-white rounded-lg shadow-2xl w-full max-w-sm overflow-hidden border border-gray-300">
        <div className="p-4 border-b border-nyt flex justify-between items-center bg-gray-50">
          <div className="font-masthead text-xl text-black">The New York Times</div>
          <button
            onClick={onClose}
            className="p-1 hover:bg-gray-200 rounded text-gray-500 hover:text-black cursor-pointer"
          >
            <X className="w-5 h-5" />
          </button>
        </div>

        <div className="p-6">
          {isLoggedIn ? (
            <div className="text-center py-4">
              <CheckCircle className="w-12 h-12 text-emerald-600 mx-auto mb-3" />
              <h4 className="font-headline text-xl font-bold text-black mb-1">
                You are Logged In
              </h4>
              <p className="text-xs text-gray-600 mb-6">
                Full All-Access Subscriber Benefits active.
              </p>
              <button
                onClick={() => {
                  onLogout();
                  onClose();
                }}
                className="w-full border border-red-300 text-red-700 hover:bg-red-50 text-xs font-bold py-2.5 rounded transition-colors cursor-pointer"
              >
                Log Out
              </button>
            </div>
          ) : (
            <>
              <div className="text-center mb-5">
                <h4 className="font-headline text-2xl font-bold text-black mb-1">
                  Log in to The Times
                </h4>
                <p className="text-xs text-gray-500">
                  Enter your email to sign in or create an account.
                </p>
              </div>

              <form onSubmit={handleSubmit} className="space-y-3">
                <div>
                  <label className="block text-xs font-semibold text-gray-700 mb-1">
                    Email address
                  </label>
                  <input
                    type="email"
                    required
                    value={email}
                    onChange={(e) => setEmail(e.target.value)}
                    placeholder="name@example.com"
                    className="w-full p-2.5 border border-gray-300 rounded text-sm focus:outline-none focus:border-black font-sans-editorial"
                  />
                </div>

                <div>
                  <label className="block text-xs font-semibold text-gray-700 mb-1">
                    Password
                  </label>
                  <input
                    type="password"
                    required
                    value={password}
                    onChange={(e) => setPassword(e.target.value)}
                    placeholder="••••••••"
                    className="w-full p-2.5 border border-gray-300 rounded text-sm focus:outline-none focus:border-black font-sans-editorial"
                  />
                </div>

                <button
                  type="submit"
                  className="w-full bg-black hover:bg-gray-800 text-white font-bold text-xs py-2.5 rounded transition-colors cursor-pointer mt-2"
                >
                  Continue
                </button>
              </form>

              <div className="mt-4 pt-4 border-t border-gray-200 text-center">
                <button
                  onClick={() => {
                    onLoginSuccess('tiewchan.yek@gmail.com');
                    onClose();
                  }}
                  className="text-xs text-blue-700 hover:underline font-medium"
                >
                  Quick Sign In as Demo Subscriber
                </button>
              </div>
            </>
          )}
        </div>
      </div>
    </div>
  );
};
